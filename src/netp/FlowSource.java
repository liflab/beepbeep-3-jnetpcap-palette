/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2016 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package netp;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import org.jnetpcap.Pcap;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JFlowKey;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.packet.JScanner;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.protocol.tcpip.Http;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.util.PcapPacketArrayList;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.tmf.Source;

public class FlowSource extends Source {
	
	private Pcap pcap;

	private PcapPacketHandler<PcapPacketArrayList> jpacketHandler = new PcapPacketHandler<PcapPacketArrayList>() {
		public void nextPacket(PcapPacket packet, PcapPacketArrayList PaketsList) {
			PaketsList.add(packet);
		}
	};

	public FlowSource(String fileName) {
		super(1);
/*
		final StringBuilder errbuf = new StringBuilder();
		pcap = Pcap.openOffline(fileName, errbuf);
		if (pcap == null) {
			try {
				throw new FileNotFoundException(errbuf.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
		
		
		/*	
		StringBuilder errbuf = new StringBuilder();  
	    final Pcap pcap = Pcap.openOffline("tests/test-http-jpeg.pcap", errbuf);  
	    if (pcap == null) {  
	        fail(errbuf.toString());  
	    }
	    try {  
	        JFlowMap map = new JFlowMap();  
	        pcap.loop(Pcap.LOOP_INFINATE, map, null);  
	        System.out.println(map.toString());  
	    } finally {  
	        pcap.close();  
	    }
		*/
	    
	    
		
        final StringBuilder errbuf = new StringBuilder();
        final Pcap pcap = Pcap.openOffline(fileName, errbuf);
        if (pcap == null) {
        	try {
				throw new FileNotFoundException(errbuf.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        pcap.loop(10, new JPacketHandler<StringBuilder>() {
            final Tcp tcp = new Tcp();
            final Http http = new Http();
            public void nextPacket(JPacket packet, StringBuilder errbuf) {
                if (packet.hasHeader(Tcp.ID)) {
                    packet.getHeader(tcp);
                    System.out.printf("tcp.dst_port=%d%n", tcp.destination());
                    System.out.printf("tcp.src_port=%d%n", tcp.source());
                    System.out.printf("tcp.ack=%x%n", tcp.ack());
                }
                if (packet.hasHeader(tcp)) {
                    System.out.printf("tcp header::%s%n", tcp.toString());
                }
                if (packet.hasHeader(tcp) && packet.hasHeader(http)) {
                    System.out.printf("http header::%s%n", http);
                }
                System.out.printf("frame #%d%n", packet.getFrameNumber());
            }
        }, errbuf);
        JScanner.getThreadLocal().setFrameNumber(0);
        final PcapPacket packet = new PcapPacket(JMemory.POINTER);
        final Tcp tcp = new Tcp();
        for (int i = 0; i < 5; i++) {
            pcap.nextEx(packet);
            if (packet.hasHeader(tcp)) {
                System.out.printf("#%d seq=%08X%n", packet.getFrameNumber(), tcp.seq());
            }
        }
        final Map<JFlowKey, JFlow> flows = new HashMap<JFlowKey, JFlow>();
        for (int i = 0; i < 50; i++) {
            pcap.nextEx(packet);
            final JFlowKey key = packet.getState().getFlowKey();
            JFlow flow = flows.get(key);
            if (flow == null) {
                flows.put(key, flow = new JFlow(key));
            }
            flow.add(new PcapPacket(packet));
        }  
		
		
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {

		PcapPacketArrayList packets = new PcapPacketArrayList();
		pcap.loop(1, jpacketHandler, packets);
		return wrapObject(packets.get(0));

	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

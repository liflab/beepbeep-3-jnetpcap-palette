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

package netp.test;

import static org.junit.Assert.*;

import org.jnetpcap.packet.JPacket;
import org.junit.Before;
import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketSource;
import netp.filters.PacketFilter;

public class PacketFilterTest {

	PacketSource source;
	PacketFilter filter;
	QueueSink sink;
	JPacket output[];
	
	@Before
    public void setUp() throws ConnectorException {
		source = new PacketSource("test.pcap");
		
		filter = new PacketFilter();
		Connector.connect(source, filter, 0, 0);
		
		sink = new QueueSink(1);
		Connector.connect(filter, sink, 0, 0);
		
		output = new JPacket[20];
    }
	
	@Test
	public void defaultFilterTest() {
		for (int i = 0; i < 10; i++) {
			source.push();
			output[0] = (JPacket) sink.remove()[0];
			assertNotNull(output[0]);
		}
	}
	
	@Test
	public void protocolTest() {
		filter.setProtocol(PacketFilter.TCP);
		for (int i = 0; i < 6; i++) {
			source.push();
			output[i] = (JPacket) sink.remove()[0];
		}
		assertNull(output[0]);
		assertNull(output[1]);
		assertNotNull(output[2]);
		assertNotNull(output[3]);
		assertNull(output[4]);
		assertNotNull(output[5]);
	}
	
	@Test
	public void ipTest() {
		filter.addSourceIp("172.16.24.213");
		for (int i = 0; i < 13; i++) {
			source.push();
			output[i] = (JPacket) sink.remove()[0];
		}
		assertNull(output[0]);
		assertNull(output[3]);
		assertNotNull(output[7]);
		assertNull(output[8]);
		assertNotNull(output[9]);
	}

}

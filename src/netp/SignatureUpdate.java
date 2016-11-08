package netp;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class SignatureUpdate extends SingleProcessor {

	protected Date firstPacketTime;
	protected Date lastPacketTime;
	protected FlowSignature lastSignature;
	
	public SignatureUpdate() {
		super(1, 1);
		firstPacketTime = null;
		lastPacketTime = null;
		lastSignature = null;
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		JPacket packet = (JPacket)inputs[0];
		FlowSignature signature = new FlowSignature();
		Date currentPacketTime = new Date(packet.getCaptureHeader().timestampInMillis());
		if (firstPacketTime == null){
			firstPacketTime = currentPacketTime;
		}
		
		Ip4 ip4 = new Ip4();
		if (!packet.hasHeader(ip4)) {
			System.out.println("No IP4 header in filtered packet");
			return new ArrayDeque<Object[]>();
		}
		
		Tcp tcp = new Tcp();
		if (!packet.hasHeader(tcp)) {
			System.out.println("no TCP header in filtered packet");
			return new ArrayDeque<Object[]>();
		}
		
		signature.setSourceIp(FormatUtils.ip(ip4.source()));
		signature.setSourcePort(tcp.source());
		signature.setDestinationIp(FormatUtils.ip(ip4.destination()));
		signature.setDestinationPort(tcp.destination());
		return null;
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

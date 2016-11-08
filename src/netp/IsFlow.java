package netp;

import java.util.Queue;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class IsFlow extends SingleProcessor{

	protected String sourceFilter, destinationFilter;
	
	public IsFlow(String sourceIp, String destinationIp) {
		super(1, 2);
		sourceFilter = sourceIp;
		destinationFilter = destinationIp;
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		JPacket packet =(JPacket)inputs[0];
		Object[] outputs = new Object[2];
		outputs[0] = inputs[0];
		outputs[1] = Boolean.FALSE;
		Ip4 ip4 = new Ip4();
		if (packet.hasHeader(ip4)) {
			String src = FormatUtils.ip(ip4.source());
			String dst = FormatUtils.ip(ip4.destination());
			if (src.equals(sourceFilter) && dst.equals(destinationFilter)){
				outputs[1] = Boolean.TRUE;
			}
		}
		return wrapVector(outputs);
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

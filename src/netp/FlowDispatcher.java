package netp;

import java.util.Queue;

import org.jnetpcap.packet.JFlowKey;
import org.jnetpcap.packet.JFlowMap;
import org.jnetpcap.packet.PcapPacket;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

/**
 * WIP class
 * Places every packet in a JFlowMap, where every packet is sorted by flow,
 * then outputs the packet.
 * The JFlowMap should probably be outside the processor, to allow other ones
 * to process it.
 */
public class FlowDispatcher extends SingleProcessor {

	public JFlowMap flowMap;

	private PcapPacket packet;
	//private JFlowKey key;

	public FlowDispatcher() {
		super(1, 1);
		flowMap = new JFlowMap();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		packet = (PcapPacket) inputs[0];
		flowMap.nextPacket(packet, null);
		/*
		key = packet.getState().getFlowKey();
		if (!flowMap.containsKey(key)){
			flowMap.put(key, new JFlow(key));
		}
		flowMap.get(key).add(packet);*/

		Object[] out = new Object[1];
		out[0] = packet;
		return wrapVector(out);
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

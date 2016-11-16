package netp.functions.flow;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;

/**
 * FlowFunction to get a network packet from a flow packet and his position
 *
 */
public class GetPacketFromPosition extends FlowIntegerFunction {

	/**
	 * @param flow The flow to extract the packet from
	 * @param i The position of the packet to extract
	 */
	@Override
	public JPacket getValue(JFlow flow, Integer i) {
		if(flow.size() > i) {
			return flow.getAll().get(i);
		}
		//TODO what to return?
		return null;
	}

}

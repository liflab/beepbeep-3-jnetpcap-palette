package netp.functions.flow;

import org.jnetpcap.packet.JFlow;

import netp.functions.flow.FlowFunction;

/**
 * FlowFunction to get a network flow size (number of packets in the flow)
 *
 */
public class GetFlowSize extends FlowFunction {

	public GetFlowSize() {
		super();
	}
	
	/**
	 * @param flow The flow to extract the packet number from
	 */
	@Override
	public Integer getValue(JFlow flow) {
		return flow.size();
	}

}

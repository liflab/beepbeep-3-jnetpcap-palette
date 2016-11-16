package netp.functions.flow;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;

import netp.functions.packet.GetTimestamp;

/**
 * BinaryFlowFunction to get the time duration between two flows
 *
 */
public class GetTimeBetweenFlows extends BinaryFlowFunction {

	public GetTimeBetweenFlows() {
		super();
	}
	
	
	/**
	 * @param firstFlow The first flow
	 * @param secondFlow The second flow
	 */
	@Override
	public Long getValue(JFlow firstFlow, JFlow secondFlow) {
		GetFlowSize flowSize = new GetFlowSize();
		Integer firstEnd = flowSize.getValue(firstFlow);
		GetPacketFromPosition firstPosition = new GetPacketFromPosition();
		GetPacketFromPosition secondPosition = new GetPacketFromPosition();
		JPacket firstPacket = firstPosition.getValue(firstFlow, firstEnd);
		JPacket secondPacket = secondPosition.getValue(firstFlow, 0);
		GetTimestamp getFirstTS = new GetTimestamp();
		Long firstTS = getFirstTS.getValue(firstPacket);
		GetTimestamp getSecondTS = new GetTimestamp();
		Long secondTS = getSecondTS.getValue(secondPacket);
		Long result = secondTS - firstTS;
		return result;
	}

}

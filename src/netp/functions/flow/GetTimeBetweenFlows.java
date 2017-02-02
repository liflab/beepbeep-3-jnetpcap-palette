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

package netp.functions.flow;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;

import ca.uqac.lif.cep.functions.BinaryFunction;
import netp.functions.packet.GetTimestamp;

/**
 * BinaryFlowFunction to get the time duration between two flows
 *
 */
public class GetTimeBetweenFlows extends BinaryFunction<JFlow, JFlow, Object> {

	public GetTimeBetweenFlows() {
		super(JFlow.class, JFlow.class, Object.class);
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

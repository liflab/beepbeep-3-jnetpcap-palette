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

/**
 * FlowIntegerFunction to get a network packet from a flow packet and his position
 *
 */
public class GetPacketFromPosition extends BinaryFunction<JFlow, Integer, Object> {

	public GetPacketFromPosition() {
		super(JFlow.class, Integer.class, Object.class);
	}

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

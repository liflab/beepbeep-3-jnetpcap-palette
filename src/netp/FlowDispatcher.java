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

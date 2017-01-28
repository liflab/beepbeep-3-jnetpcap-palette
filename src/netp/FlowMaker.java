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
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.PcapPacket;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

/**
 * Places input packets in JFlowMap (packets are thus sorted by flow), then
 * outputs its corresponding flow.
 */

public class FlowMaker extends SingleProcessor {

	private JFlowMap flowMap;
	private JPacket packet;
	private JFlowKey key;

	public FlowMaker() {
		super(1, 1);
		flowMap = new JFlowMap();
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		packet = (JPacket) inputs[0];

		// store packet in flow map
		flowMap.nextPacket((PcapPacket) packet, null);
		
		// output flow
		key = packet.getState().getFlowKey();
		Object[] out = new Object[1];
		out[0] = flowMap.get(key);
		return wrapVector(out);

		// TODO output flow only after a succession of packets from the same
		// flow has ended.
	}

	@Override
	public Processor clone() {
		FlowMaker clone = new FlowMaker();
		clone.flowMap = (JFlowMap) flowMap.clone();
		return clone;
	}

}

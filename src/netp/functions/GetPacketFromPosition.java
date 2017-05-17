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

package netp.functions;

import java.util.Set;

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.SimpleFunction;

/**
 * FlowIntegerFunction to get a network packet from a flow packet and his
 * position
 *
 */
public class GetPacketFromPosition extends SimpleFunction {

	private JFlow flow;
	private int position;

	public GetPacketFromPosition() {

	}

	@Override
	public void compute(Object[] inputs, Object[] outputs) {
		flow = (JFlow) inputs[0];
		position = (int) inputs[1];
		if (0 <= position && position < flow.size()) {
			outputs[0] = flow.getAll().get(position);
		} else {
			// TODO throw exception
		}
	}

	@Override
	public int getInputArity() {
		return 2;
	}

	@Override
	public int getOutputArity() {
		return 1;
	}

	@Override
	public void reset() {
		// nothing

	}

	@Override
	public Function clone() {
		return new GetPacketFromPosition();
	}

	@Override
	public void getInputTypesFor(Set<Class<?>> classes, int index) {
		switch (index) {
		case 0:
			classes.add(JFlow.class);
			break;
		case 1:
			classes.add(Integer.class);
			break;
		}
	}

	@Override
	public Class<?> getOutputTypeFor(int index) {
		return JPacket.class;
	}

}

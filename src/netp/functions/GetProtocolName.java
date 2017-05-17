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

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.SimpleFunction;

/**
 * PacketFunction to get the protocol name of a network packet
 *
 */
public class GetProtocolName extends SimpleFunction {

	private GetProtocolId protocolId;
	private int id;
	private String result;

	public GetProtocolName() {
		super();
		protocolId = new GetProtocolId();
	}

	@Override
	public void compute(Object[] inputs, Object[] outputs) {
		protocolId.compute(inputs, outputs);
		id = (int) outputs[0];
		switch (id) {
		case 1:
			result = "ICMP";
			break;
		case 4:
			result = "IPv4";
			break;
		case 6:
			result = "TCP";
			break;
		case 17:
			result = "UDP";
			break;
		default:
			result = "Undefined";
			break;
		}
		outputs[0] = result;
	}

	@Override
	public int getInputArity() {
		return 1;
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
		return new GetProtocolName();
	}

	@Override
	public void getInputTypesFor(Set<Class<?>> classes, int index) {
		classes.add(JPacket.class);
	}

	@Override
	public Class<?> getOutputTypeFor(int index) {
		return String.class;
	}

}

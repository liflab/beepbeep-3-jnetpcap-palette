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
import org.jnetpcap.protocol.network.Ip4;

import ca.uqac.lif.cep.functions.Function;
import ca.uqac.lif.cep.functions.SimpleFunction;

/**
 * PacketFunction to get destination IP from a network packet
 *
 */
public class GetDestinationIp extends SimpleFunction {

	private Ip4 ip4;
	private JPacket packet;

	public GetDestinationIp() {
		super();
		ip4 = new Ip4();
	}

	@Override
	public void compute(Object[] inputs, Object[] outputs) {
		packet = (JPacket) inputs[0];
		if (packet.hasHeader(ip4)) {
			outputs[0] = FormatUtils.ip(ip4.destination());
		} else {
			// TODO throw exception
		}

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
	public Function clone() {
		return new GetDestinationIp();
	}

	@Override
	public void getInputTypesFor(Set<Class<?>> classes, int index) {
		classes.add(JPacket.class);
	}

	@Override
	public Class<?> getOutputTypeFor(int index) {
		return String.class;
	}

	@Override
	public void reset() {
		// nothing
	}

}

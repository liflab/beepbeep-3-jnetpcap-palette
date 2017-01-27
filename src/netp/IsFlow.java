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

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class IsFlow extends SingleProcessor{

	protected String sourceFilter, destinationFilter;
	
	public IsFlow(String sourceIp, String destinationIp) {
		super(1, 2);
		sourceFilter = sourceIp;
		destinationFilter = destinationIp;
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		JPacket packet =(JPacket)inputs[0];
		Object[] outputs = new Object[2];
		outputs[0] = inputs[0];
		outputs[1] = Boolean.FALSE;
		Ip4 ip4 = new Ip4();
		if (packet.hasHeader(ip4)) {
			String src = FormatUtils.ip(ip4.source());
			String dst = FormatUtils.ip(ip4.destination());
			if (src.equals(sourceFilter) && dst.equals(destinationFilter)){
				outputs[1] = Boolean.TRUE;
			}
		}
		return wrapVector(outputs);
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

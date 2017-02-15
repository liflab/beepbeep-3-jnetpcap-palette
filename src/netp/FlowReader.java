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

import org.jnetpcap.packet.JFlow;
import org.jnetpcap.packet.JPacket;

import ca.uqac.lif.cep.functions.BinaryFunction;
import ca.uqac.lif.cep.functions.FunctionProcessor;
import netp.functions.flow.FlowFunction;

/**
 * FunctionProcessor used to extract information from a network flow
 */
public class FlowReader extends FunctionProcessor {

	/**
	 * @param function The computable FlowFunction responsible for the computation
	 */
	public FlowReader(FlowFunction function) {
		super(function);
	}
	
	/**
	 * @param function The computable BinaryFlowFunction responsible for the computation
	 */
	public FlowReader(BinaryFunction<JFlow, Integer, JPacket> function) {
		super(function);
	}
	
}

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

import ca.uqac.lif.cep.functions.BinaryFunction;

/**
 * Abstact function used to extract information from two network flows
 *
 */
public abstract class FlowIntegerFunction extends BinaryFunction<JFlow, Integer, Object> {

	/**
	 * 
	 * @param input1 The first input JFlow element of the function
	 * @param input2 The second input JFlow element of the function
	 * @param output The output of the function
	 */
	public FlowIntegerFunction() {
		super(JFlow.class, Integer.class, Object.class);
	}
	
}
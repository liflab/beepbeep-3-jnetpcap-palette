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

import org.jnetpcap.packet.JFlow;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class FlowMaker extends SingleProcessor {
	
	private JFlow oldFlow;

	public FlowMaker() {
		super(1, 1);
		oldFlow = null;
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		JFlow newFlow = (JFlow) inputs[0];
		if (oldFlow == null) {
			// if it is the first flow received, replace the old flow
			oldFlow = newFlow;
		}
		if (oldFlow != newFlow) {
			// if the two flows are different
			JFlow flow = oldFlow;
			oldFlow = newFlow;
			
			// return the old flow
			Object[] out = new Object[1];
			out[0] = flow;
			return wrapVector(out);
		}
		return null;
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

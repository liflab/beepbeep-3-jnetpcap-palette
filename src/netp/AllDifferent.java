
package netp;

import java.util.LinkedList;
import java.util.Queue;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class AllDifferent extends SingleProcessor {

	int m_width;

	LinkedList<Object> m_previousInputs;

	public AllDifferent() {
		super(1, 1);
		m_width = 0;
		m_previousInputs = new LinkedList<Object>();
	}

	public AllDifferent(int width) {
		super(1, 1);
		m_width = width;
		m_previousInputs = new LinkedList<Object>();
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		Object[] out = new Object[1];
		out[0] = Boolean.TRUE;
		for (Object previous : m_previousInputs) {
			if (previous.equals(inputs[0])) {
				out[0] = Boolean.FALSE;
				break;
			}
		}
		if (m_width > 0 && m_previousInputs.size() >= m_width){
			m_previousInputs.remove();
		}
		m_previousInputs.add(inputs[0]);
		return wrapObject(out);
	}

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}

}

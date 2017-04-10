
package netp;

import java.util.LinkedList;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Compares every previous entries on a one-to-one basis. This function takes 
 * width as an argument which specifies the number of entries to compare.
 * Complexity O(n)
 */
public class AllDifferent extends UnaryFunction<Object, Boolean> {

	private int m_width;
	private int m_falseCount;
	
	private boolean allDifferent;

	private LinkedList<Object> m_previousInputs;

	/**
	 * Instantiate this function with an infinite width.
	 */
	public AllDifferent() {
		this(0);
	}

	/**
	 * @param width The number of entries to compare one-to-one. 0 or less means infinite width.
	 */
	public AllDifferent(int width) {
		super(Object.class, Boolean.class);
		m_width = Math.max(0, width);
		m_falseCount = 0;
		m_previousInputs = new LinkedList<Object>();
	}

	@Override
	public Boolean getValue(Object x) {
		
		// if a duplicate entry has been found and the width is infinite.
		if (m_falseCount > 0 && m_width <= 0)
			return false;
		
		allDifferent = true;
		
		// if duplicate entries were found in previous calls
		if (m_falseCount > 0) {
			m_falseCount--;
			allDifferent = false;
		}
		
		// compare this entry to every previous one
		for (int i = m_previousInputs.size() - 1; i >= 0; i--) {
			if (m_previousInputs.get(i).equals(x)){
				m_falseCount = Math.max(m_falseCount, i);  // this function will return false at least until this entry is removed
				allDifferent = false;
				break;
			}
		}
		
		// update previous entries
		m_previousInputs.add(x);
		if (m_width > 0 && m_previousInputs.size() >= m_width) {
			m_previousInputs.remove();
		}
		
		return allDifferent;
	}
	
	public int getWidth(){
		return m_width;
	}

}

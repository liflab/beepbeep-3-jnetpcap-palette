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
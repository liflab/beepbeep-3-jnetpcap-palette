package netp.functions.flow;

import org.jnetpcap.packet.JFlow;

import ca.uqac.lif.cep.functions.BinaryFunction;

/**
 * Abstact function used to extract information from two network flows
 *
 */
public abstract class BinaryFlowFunction extends BinaryFunction<JFlow, JFlow, Object> {

	/**
	 * 
	 * @param input1 The first input JFlow element of the function
	 * @param input2 The second input JFlow element of the function
	 * @param output The output of the function
	 */
	public BinaryFlowFunction() {
		super(JFlow.class, JFlow.class, Object.class);
	}
	
}
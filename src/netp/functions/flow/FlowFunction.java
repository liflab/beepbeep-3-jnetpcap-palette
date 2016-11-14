package netp.functions.flow;


import org.jnetpcap.packet.JFlow;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Abstact function used to extract information from a network flow
 *
 */
public abstract class FlowFunction extends UnaryFunction<JFlow, String> {

	/**
	 * 
	 * @param input The input JFlow element of the function
	 * @param output The String output of the function
	 */
	public FlowFunction() {
		super(JFlow.class,  String.class);
	}
	
}

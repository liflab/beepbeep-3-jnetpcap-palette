package netp;

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

}

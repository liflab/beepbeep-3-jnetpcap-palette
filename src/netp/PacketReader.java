package netp;

import ca.uqac.lif.cep.functions.*;

/**
 * 
 * @author plfaure
 *
 */
public class PacketReader extends FunctionProcessor {

	/**
	 * @param function The computable PacketFunction responsible for the computation
	 */
	public PacketReader(PacketFunction function) {
		super(function);
	}

}

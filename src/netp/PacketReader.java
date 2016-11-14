package netp;

import ca.uqac.lif.cep.functions.*;
import netp.functions.packet.PacketFunction;

/**
 * FunctionProcessor used to extract information from a network packet
 */
public class PacketReader extends FunctionProcessor {

	/**
	 * @param function The computable PacketFunction responsible for the computation
	 */
	public PacketReader(PacketFunction function) {
		super(function);
	}

}

package netp;

import org.jnetpcap.packet.JPacket;

import ca.uqac.lif.cep.functions.UnaryFunction;

/**
 * Abstact function used to extract information from a network packet
 *
 */
public abstract class PacketFunction extends UnaryFunction<JPacket, String> {

	/**
	 * 
	 * @param input The input JPacket element of the function
	 * @param output The String output of the function
	 */
	public PacketFunction(Class<JPacket> input, Class<String> output) {
		super(input, output);
	}
	
}

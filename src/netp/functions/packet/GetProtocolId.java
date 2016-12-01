package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get the protocol id of a network packet
 *
 */
public class GetProtocolId extends PacketFunction {

	public GetProtocolId() {
		super();
	}

	/**
	 * @param packet The packet to extract protocol id from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		Ip4 ip4 = new Ip4();
		// TODO what if it isn't IPv4?
		if (packet.hasHeader(ip4)) {
			return ip4.type();
		}
		return null;
	}

}

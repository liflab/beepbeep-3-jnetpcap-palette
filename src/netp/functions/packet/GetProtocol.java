package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get the protocol of a network packet
 *
 */
public class GetProtocol extends PacketFunction {

	public GetProtocol() {
		super();
	}

	/**
	 * @param packet The packet to extract protocol from
	 */
	@Override
	public String getValue(JPacket packet) {
		Ip4 ip4 = new Ip4();
		if (packet.hasHeader(ip4)) {
			return Integer.toString(ip4.type());
		}
		return null;
	}

}

package netp;

import org.jnetpcap.packet.JPacket;

/**
 * PacketFunction to get a network packet size
 *
 */
public class GetPacketSize extends PacketFunction {

	public GetPacketSize() {
		super();
	}

	/**
	 * @param packet The packet to extract the size length from
	 */
	@Override
	public String getValue(JPacket packet) {
		return Integer.toString(packet.size());
	}

}

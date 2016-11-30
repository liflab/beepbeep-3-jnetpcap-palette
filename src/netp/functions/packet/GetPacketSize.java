package netp.functions.packet;

import org.jnetpcap.packet.JPacket;

/**
 * PacketFunction to get a network packet size (in bytes)
 *
 */
public class GetPacketSize extends PacketFunction {

	public GetPacketSize() {
		super();
	}

	/**
	 * @param packet The packet to extract the size length (in bytes) from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		return packet.size();
	}

}

package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.Payload;

/**
 * PacketFunction to get payload packet lenght of a network packet (in bytes)
 *
 */
public class GetPayloadLength extends PacketFunction {

	public GetPayloadLength() {
		super();
	}

	/**
	 * @param packet The packet to extract payload length (in bytes) from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		Payload payload = new Payload();
		if (packet.hasHeader(payload)) {
			return payload.getLength();
		}
		return null;
	}

}

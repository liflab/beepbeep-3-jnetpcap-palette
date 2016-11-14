package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.Payload;

/**
 * PacketFunction to get payload packet lenght of a network packet
 *
 */
public class GetPayloadLength extends PacketFunction {

	public GetPayloadLength() {
		super();
	}

	/**
	 * @param packet The packet to extract payload length from
	 */
	@Override
	public String getValue(JPacket packet) {
		Payload payload = new Payload();
		if (packet.hasHeader(payload)) {
			return Integer.toString(payload.getLength());
		}
		return null;
	}

}

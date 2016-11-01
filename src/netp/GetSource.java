package netp;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get source from a network packet
 *
 */
public class GetSource extends PacketFunction {

	public GetSource(Class<JPacket> input, Class<String> output) {
		super(input, output);
	}

	/**
	 * @param packet The packet to extract source from
	 */
	@Override
	public String getValue(JPacket packet) {
		Ip4 ip4 = new Ip4();
		if (packet.hasHeader(ip4)) {
			return FormatUtils.ip(ip4.source());
		}
		return null;
	}

}

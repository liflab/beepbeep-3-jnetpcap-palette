package netp;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get source IP from a network packet
 *
 */
public class GetSourceIp extends PacketFunction {

	public GetSourceIp() {
		super();
	}

	/**
	 * @param packet The packet to extract source IP from
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

package netp;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get destination IP from a network packet
 *
 */
public class GetDestinationIp extends PacketFunction {

	public GetDestinationIp() {
		super();
	}

	/**
	 * @param packet The packet to extract destination IP from
	 */
	@Override
	public String getValue(JPacket packet) {
		Ip4 ip4 = new Ip4();
		if (packet.hasHeader(ip4)) {
			return FormatUtils.ip(ip4.destination());
		}
		return null;
	}

}

package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * PacketFunction to get destination port from a network packet
 *
 */
public class GetDestinationPort extends PacketFunction {

	public GetDestinationPort() {
		super();
	}

	/**
	 * @param packet The packet to extract destination port from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		Tcp tcp = new Tcp();
		Udp udp = new Udp();
		if (packet.hasHeader(tcp)) {
			return tcp.destination();
		}
		if (packet.hasHeader(udp)) {
			return udp.destination();
		}
		return null;
	}

}

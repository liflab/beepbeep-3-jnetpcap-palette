package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * PacketFunction to get source port from a network packet
 *
 */
public class GetSourcePort extends PacketFunction {

	public GetSourcePort() {
		super();
	}

	/**
	 * @param packet The packet to extract source port from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		Tcp tcp = new Tcp();
		Udp udp = new Udp();
		if (packet.hasHeader(tcp)) {
			return tcp.source();
		}
		if (packet.hasHeader(udp)) {
			return udp.source();
		}
		return null;
	}

}

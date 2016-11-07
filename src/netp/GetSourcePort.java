package netp;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;

/**
 * PacketFunction to get source from a network packet
 *
 */
public class GetSourcePort extends PacketFunction {

	public GetSourcePort() {
		super();
	}

	/**
	 * @param packet The packet to extract source from
	 */
	@Override
	public String getValue(JPacket packet) {
		Tcp tcp = new Tcp();
		if (packet.hasHeader(tcp)) {
			return Integer.toString(tcp.source());
		}
		return null;
	}

}

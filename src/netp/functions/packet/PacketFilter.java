package netp.functions.packet;

import java.util.ArrayList;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

import ca.uqac.lif.cep.functions.UnaryFunction;

public class PacketFilter extends UnaryFunction<JPacket, JPacket> {
	private int protocol;
	private ArrayList<byte[]> sourceIp;
	private int sourcePortLow;
	private int sourcePortHigh;
	private ArrayList<byte[]> destinationIp;
	private int destinationPortLow;
	private int destinationPortHigh;

	private Ip4 ip4;
	private Tcp tcp;
	private Udp udp;
	private boolean found;

	public PacketFilter() {
		super(JPacket.class, JPacket.class);
	}

	@Override
	public JPacket getValue(JPacket packet) {
		ip4 = new Ip4(); // retrieve IP header
		if (!packet.hasHeader(ip4)) {
			return null;
		}

		// check protocol
		if (protocol > 0 && ip4.type() != protocol) {
			return null;
		}

		// check source and destination ports
		if (protocol == 6) {
			tcp = new Tcp();
			if (!packet.hasHeader(tcp)) {
				return null;
			}
			if (!(sourcePortLow <= tcp.source() && tcp.source() <= sourcePortHigh)
					|| !(destinationPortLow <= tcp.destination() && tcp.destination() <= destinationPortHigh)) {
				return null;
			}
		}
		if (protocol == 17) {
			udp = new Udp();
			if (!packet.hasHeader(udp)) {
				return null;
			}
			if (!(sourcePortLow <= udp.source() && udp.source() <= sourcePortHigh)
					|| !(destinationPortLow <= udp.destination() && udp.destination() <= destinationPortHigh)) {
				return null;
			}
		}

		// check source and destination IPs
		found = false;
		for (byte[] ip : sourceIp){
			if (ip.equals(ip4.source())){
				found = true;
				break;
			}
		}
		if (!found){
			return null;
		}
		
		found = false;
		for (byte[] ip : destinationIp){
			if (ip.equals(ip4.destination())){
				found = true;
				break;
			}
		}
		if (!found){
			return null;
		}

		// Packet went through the filter
		return packet;
	}

}

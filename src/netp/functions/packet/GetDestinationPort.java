/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2016 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package netp.functions.packet;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.tcpip.Tcp;
import org.jnetpcap.protocol.tcpip.Udp;

/**
 * PacketFunction to get destination port from a network packet
 *
 */
public class GetDestinationPort extends PacketFunction {

	private Tcp tcp;
	private Udp udp;

	public GetDestinationPort() {
		super();
		tcp = new Tcp();
		udp = new Udp();
	}

	/**
	 * @param packet
	 *            The packet to extract destination port from
	 */
	@Override
	public Integer getValue(JPacket packet) {

		if (packet.hasHeader(tcp)) {
			return tcp.destination();
		}
		if (packet.hasHeader(udp)) {
			return udp.destination();
		}
		return null;
	}

}

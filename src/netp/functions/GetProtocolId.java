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

package netp.functions;

import org.jnetpcap.packet.JPacket;
import org.jnetpcap.protocol.network.Ip4;

/**
 * PacketFunction to get the protocol id of a network packet
 *
 */
public class GetProtocolId extends PacketFunction {

	private Ip4 ip4;
	
	public GetProtocolId() {
		super();
		ip4 = new Ip4();
	}

	/**
	 * @param packet The packet to extract protocol id from
	 */
	@Override
	public Integer getValue(JPacket packet) {
		// TODO what if it isn't IPv4?
		if (packet.hasHeader(ip4)) {
			return ip4.type();
		}
		return null;
	}

}

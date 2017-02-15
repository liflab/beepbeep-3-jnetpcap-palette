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

/**
 * PacketFunction to get the protocol name of a network packet
 *
 */
public class GetProtocolName extends PacketFunction {

	private GetProtocolId protocolId;
	private int id;
	private String result;

	public GetProtocolName() {
		super();
		protocolId = new GetProtocolId();
	}

	/**
	 * @param packet
	 *            The packet to extract protocol name from
	 */
	@Override
	public String getValue(JPacket packet) {

		id = protocolId.getValue(packet);

		// TODO add cases if necessary
		switch (id) {
		case 4:
			result = "IPv4";
			break;
		case 6:
			result = "TCP";
			break;
		case 17:
			result = "UDP";
			break;
		default:
			result = "undefined";
			break;
		}
		return result;
	}

}

package netp.functions.packet;

import org.jnetpcap.packet.JPacket;

/**
 * PacketFunction to get the timestamp of a network packet
 * 
 */
public class GetTimestamp extends PacketFunction {

	public GetTimestamp() {
		super();
	}
	
	/**
	 * @param packet The packet to timestamp from
	 */
	@Override
	public Long getValue(JPacket packet) {
		return packet.getCaptureHeader().timestampInMillis();
	}

}

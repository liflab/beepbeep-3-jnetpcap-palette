package netp.functions.packet;

import org.jnetpcap.packet.JPacket;

/**
 * PacketFunction to get the protocol name of a network packet
 *
 */
public class GetProtocolName extends PacketFunction {

	public GetProtocolName() {
		super();
	}
	
	/**
	 * @param packet The packet to extract protocol name from
	 */
	@Override
	public String getValue(JPacket packet) {
		GetProtocolId protocolId = new GetProtocolId();
		Integer id = protocolId.getValue(packet);
		String result;
		
		// TODO add cases if necessary
		switch (id) {
	    	case 4:	 result = "IPv4";
	        		 break;
	        case 6:	 result = "TCP";
	        		 break;
	        case 17: result = "UDP";
	        		 break;
	        default: result = "";
	        		 break;
		}
		return result;
	}

}

package netp;

public class FlowSignature {
	
	/**
	 * Description
	 */
	private String attribute;
	
	/**
	 * Flow source IP address
	 */
	private String sourceIp;
	
	/**
	 * Flow source port address
	 */
	private int sourcePort;
	
	/**
	 * Flow destination IP address
	 */
	private String destinationIp;
	
	/**
	 * Flow destination port address
	 */
	private int destinationPort;
	
	/**
	 * Transport layer protocol or mixed
	 */
	private String protocol;
	
	/**
	 * Average payload packet length for time interval
	 */
	private float averagePayloadLength;
	
	/**
	 * Variance of payload packet length for time interval
	 */
	private float payloadLengthVariance;
	
	/**
	 * Number of packets exchanged for time interval
	 */
	private int packetCount;
	
	/**
	 * Number of packets exchanged per second in time interval
	 */
	private float packetPerSecond;
	
	/**
	 * The size of the first packet in the flow
	 */
	private int firstPacketSize;
	
	/**
	 * The average time between packets in time interval
	 */
	private float averageTimeBetweenPacket;
	
	/**
	 * The number of reconnects for a flow
	 */
	private int reconnectCount;
	
	/**
	 * Number of flows from this address over the total number of flows generated per hour
	 */
	private float flowPerHour;

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	public int getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(int sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getDestinationIp() {
		return destinationIp;
	}

	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}

	public int getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public float getAveragePayloadLength() {
		return averagePayloadLength;
	}

	public void setAveragePayloadLength(float averagePayloadLength) {
		this.averagePayloadLength = averagePayloadLength;
	}

	public float getPayloadLengthVariance() {
		return payloadLengthVariance;
	}

	public void setPayloadLengthVariance(float payloadLengthVariance) {
		this.payloadLengthVariance = payloadLengthVariance;
	}

	public int getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}

	public float getPacketPerSecond() {
		return packetPerSecond;
	}

	public void setPacketPerSecond(float packetPerSecond) {
		this.packetPerSecond = packetPerSecond;
	}

	public int getFirstPacketSize() {
		return firstPacketSize;
	}

	public void setFirstPacketSize(int firstPacketSize) {
		this.firstPacketSize = firstPacketSize;
	}

	public float getAverageTimeBetweenPacket() {
		return averageTimeBetweenPacket;
	}

	public void setAverageTimeBetweenPacket(float averageTimeBetweenPacket) {
		this.averageTimeBetweenPacket = averageTimeBetweenPacket;
	}

	public int getReconnectCount() {
		return reconnectCount;
	}

	public void setReconnectCount(int reconnectCount) {
		this.reconnectCount = reconnectCount;
	}

	public float getFlowPerHour() {
		return flowPerHour;
	}

	public void setFlowPerHour(float flowPerHour) {
		this.flowPerHour = flowPerHour;
	}
}

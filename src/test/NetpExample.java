package test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketFilter;
import netp.PacketReader;
import netp.PacketSource;
import netp.PayloadFilter;
import netp.functions.packet.GetSourceIp;

public class NetpExample {
	
	/**
	 * This code displays the source ip only from UDP packets containing byte 24 and 99
	 */
	public static void main(String[] args) throws ConnectorException {

		// outputs packets from a pcap file
		PacketSource source = new PacketSource("test.pcap");

		// only keep UDP packets
		PacketFilter packetFilter = new PacketFilter();
		packetFilter.setProtocol(PacketFilter.UDP);
		Connector.connect(source, packetFilter, 0, 0);

		// only keep packets containing bytes 24 99 in payload
		PayloadFilter payloadFilter = new PayloadFilter();
		payloadFilter.setFilter("2499");
		Connector.connect(packetFilter, payloadFilter, 0, 0);

		// extract source IP address of packet
		PacketReader sourceIp = new PacketReader(new GetSourceIp());
		Connector.connect(payloadFilter, sourceIp, 0, 0);

		// retrieve results
		QueueSink sink = new QueueSink(1);
		Connector.connect(sourceIp, sink, 0, 0);

		// compute the first 15 packets
		for (int i = 0; i < 15; i++) {
			source.push();
			String output = (String) sink.remove()[0];
			if (output != null) // only display the packets that went through
								// the filters
				System.out.println(i + ": " + output);
		}
	}

}

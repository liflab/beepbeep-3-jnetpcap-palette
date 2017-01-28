package examples;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.NetworkInterfaceSource;
import netp.PacketReader;
import netp.functions.packet.GetSourceIp;

/**
 * Shows how to capture packets live from a network interface, and displays their source ip.
 */

public class LiveCapture {

	public static void main(String[] args) throws Exception {
		// outputs packets from a network interface
		NetworkInterfaceSource source = new NetworkInterfaceSource("any");

		// extract source IP address of packet
		PacketReader sourceIp = new PacketReader(new GetSourceIp());
		Connector.connect(source, sourceIp, 0, 0);

		// retrieve results
		QueueSink sink = new QueueSink(1);
		Connector.connect(sourceIp, sink, 0, 0);

		// compute the first 100 packets
		for (int i = 0; i < 100; i++) {
			source.push();
			String output = (String) sink.remove()[0];
			System.out.println(i + ": " + output);
		}

	}

}

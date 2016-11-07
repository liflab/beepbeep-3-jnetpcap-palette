package test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.GetSourceIp;
import netp.PacketReader;
import netp.PacketSource;

public class NetpTest {

	public static void main(String[] args) throws ConnectorException {
		PacketSource source = new PacketSource("test.pcap");
		PacketReader processor = new PacketReader(new GetSourceIp());
		Connector.connect(source, processor, 0, 0);

		QueueSink sink = new QueueSink(1);
		Connector.connect(processor, sink, 0, 0);

		for (int i = 0; i < 10; i++) {
			source.push();
			String output = (String) sink.remove()[0];
			System.out.println(output);
		}

	}

}

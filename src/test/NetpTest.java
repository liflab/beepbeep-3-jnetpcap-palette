package test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.Filter;
import ca.uqac.lif.cep.tmf.Fork;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.GetSourceIp;
import netp.IsFlow;
import netp.PacketReader;
import netp.PacketSource;

public class NetpTest {

	public static void main(String[] args) throws ConnectorException {
		PacketSource source = new PacketSource("test.pcap");

		IsFlow isFlow = new IsFlow("172.16.24.213", "162.213.33.50");
		Connector.connect(source, isFlow, 0, 0);
		
		Filter filter = new Filter();
		Connector.connect(isFlow, filter, 0, 0);
		Connector.connect(isFlow, filter, 1, 1);
		
		PacketReader srcIp = new PacketReader(new GetSourceIp());
		Connector.connect(filter, srcIp, 0, 0);
		
		QueueSink sink = new QueueSink(1);
		Connector.connect(srcIp, sink, 0, 0);
		

		for (int i = 0; i < 15; i++) {
			source.push();
			String output = (String) sink.remove()[0];
			System.out.println(output);
		}
	}
}

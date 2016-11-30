package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetDestinationPort;

public class GetDestinationPortTest {

	@Test
	public void getDestinationPortTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader destPort = new PacketReader(new GetDestinationPort());
		try {
			Connector.connect(source, destPort, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(destPort, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Integer output = (Integer) sink.remove()[0];
		System.out.println(output);
		
		Integer expected = 6666;
		assertEquals(expected, output);
	}

}

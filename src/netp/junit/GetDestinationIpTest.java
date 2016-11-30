package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.*;

public class GetDestinationIpTest {

	@Test
	public void getDestinationIpTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader destIp = new PacketReader(new GetDestinationIp());
		try {
			Connector.connect(source, destIp, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(destIp, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		String output = (String) sink.remove()[0];
		System.out.println(output);
		
		String expected = "230.0.0.1";
		assertEquals(expected, output);
	}

}

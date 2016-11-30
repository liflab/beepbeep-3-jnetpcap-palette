package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetSourcePort;

public class GetSourcePortTest {

	@Test
	public void getSourcePortTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader srcPort = new PacketReader(new GetSourcePort());
		try {
			Connector.connect(source, srcPort, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(srcPort, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Integer output = (Integer) sink.remove()[0];
		System.out.println(output);
		
		Integer expected = 50696;
		assertEquals(expected, output);
	}

}

package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetProtocolId;

public class GetProtocolIdTest {

	@Test
	public void getProtocolIdTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader protocol = new PacketReader(new GetProtocolId());
		try {
			Connector.connect(source, protocol, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(protocol, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Integer output = (Integer) sink.remove()[0];
		System.out.println(output);
		
		Integer expected = 17;
		assertEquals(expected, output);
	}

}

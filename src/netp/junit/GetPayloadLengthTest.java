package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetPayloadLength;

public class GetPayloadLengthTest {

	@Test
	public void getPayloadLengthTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader ploadL = new PacketReader(new GetPayloadLength());
		try {
			Connector.connect(source, ploadL, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(ploadL, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Integer output = (Integer) sink.remove()[0];
		System.out.println(output);
		
		Integer expected = 511;
		assertEquals(expected, output);
	}

}

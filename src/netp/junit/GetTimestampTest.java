package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetTimestamp;

public class GetTimestampTest {

	@Test
	public void getTimestampTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader timestp = new PacketReader(new GetTimestamp());
		try {
			Connector.connect(source, timestp, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(timestp, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Long output = (Long) sink.remove()[0];
		System.out.println(output);
		
		Long expected = 1478555321945L;
		assertEquals(expected, output);
	}

}

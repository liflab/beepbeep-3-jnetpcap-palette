package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetProtocolName;

public class GetProtocolNameTest {

	@Test
	public void getProtocolNameTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader protocol = new PacketReader(new GetProtocolName());
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
		String output = (String) sink.remove()[0];
		System.out.println(output);
		
		String expected = "UDP";
		assertEquals(expected, output);
	}

}

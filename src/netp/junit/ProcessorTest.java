package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.GetSourceIp;
import netp.PacketReader;
import netp.PacketSource;

public class ProcessorTest {

	@Test
	public void packetSourceTest() throws ConnectorException {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader srcIp = new PacketReader(new GetSourceIp());
		Connector.connect(source, srcIp, 0, 0);

		QueueSink sink = new QueueSink(1);
		Connector.connect(srcIp, sink, 0, 0);

		source.push();
		String output = (String) sink.remove()[0];
		System.out.println(output);
		assertEquals("172.16.24.194", output);
	}
}

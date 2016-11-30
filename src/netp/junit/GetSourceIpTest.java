package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetSourceIp;

public class GetSourceIpTest {

	@Test
	public void getSourceIpTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader srcIp = new PacketReader(new GetSourceIp());
		try {
			Connector.connect(source, srcIp, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(srcIp, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		String output = (String) sink.remove()[0];
		System.out.println(output);
		
		String expected = "172.16.24.194";
		assertEquals(expected, output);
	}

}

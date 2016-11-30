package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.PacketReader;
import netp.PacketSource;
import netp.functions.packet.GetPacketSize;

public class GetPacketSizeTest {

	@Test
	public void getPacketSizeTest() {
		PacketSource source = new PacketSource("test.pcap");

		PacketReader packetSize = new PacketReader(new GetPacketSize());
		try {
			Connector.connect(source, packetSize, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(packetSize, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		source.push();
		Integer output = (Integer) sink.remove()[0];
		System.out.println(output);
		
		Integer expected = 553;
		assertEquals(expected, output);
	}

}

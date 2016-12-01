package netp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.FlowReader;
import netp.PacketSource;
import netp.functions.flow.GetFlowSize;

public class GetFlowSizeTest {

	@Test
	public void getFlowSizeTest() {
		PacketSource source = new PacketSource("test.pcap");

		FlowReader flowSize = new FlowReader(new GetFlowSize());
		try {
			Connector.connect(source, flowSize, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(flowSize, sink, 0, 0);
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

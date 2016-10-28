package bbtest;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import ca.uqac.lif.cep.tmf.QueueSource;

public class Main {

	public static void main(String[] args) throws ConnectorException {
		QueueSource source = new QueueSource(1);
		source.addEvent(new Point2D(1,3));
		SplitPoint processor = new SplitPoint();
		Connector.connect(source, processor, 0, 0);

		QueueSink sink1 = new QueueSink(1);
		Connector.connect(processor, sink1, 0, 0);
		QueueSink sink2 = new QueueSink(1);
		Connector.connect(processor, sink2, 1, 0);

		source.push();
		float output = (Float) sink1.remove()[0];
		System.out.println(output);
		output = (Float) sink2.remove()[0];
		System.out.println(output);
	}
}
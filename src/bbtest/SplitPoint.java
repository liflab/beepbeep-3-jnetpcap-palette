package bbtest;

import java.util.Queue;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class SplitPoint extends SingleProcessor {

  public SplitPoint() {
    super(1, 2);
  }

  public Queue<Object[]> compute(Object[] inputs) {
    Point2D p = (Point2D) inputs[0];
    Float[] output_event = new Float[2];
    output_event[0] = p.x;
    output_event[1] = p.y;
    return wrapVector(output_event);
  }

@Override
public Processor clone() {
	// TODO Auto-generated method stub
	return null;
}
}
package bbtest;

import java.util.Queue;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class EuclidianDistance extends SingleProcessor {

	public EuclidianDistance() {
		super(2, 1);
	}

	@Override
	protected Queue<Object[]> compute(Object[] inputs) {
		Point2D p1 = (Point2D) inputs[0];
		Point2D p2 = (Point2D) inputs[1];
		float distance = (float) Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
		return wrapObject(distance);
	}

	@Override
	public Processor clone() {
		return null;
	}

}

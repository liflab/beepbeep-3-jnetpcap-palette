package bbtest;

import java.util.Queue;

import ca.uqac.lif.cep.Processor;
import ca.uqac.lif.cep.SingleProcessor;

public class StringLength extends SingleProcessor {
	
  public StringLength() {
    super(1, 1);
  }

  public Queue<Object[]> compute(Object[] inputs) {
    int length = ((String) inputs[0]).length();
    return wrapObject(length);
  }

	@Override
	public Processor clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
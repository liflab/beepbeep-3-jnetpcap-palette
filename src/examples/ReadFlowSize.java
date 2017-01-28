/*
    BeepBeep, an event stream processor
    Copyright (C) 2008-2016 Sylvain Hallé

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package examples;

import ca.uqac.lif.cep.Connector;
import ca.uqac.lif.cep.Connector.ConnectorException;
import ca.uqac.lif.cep.tmf.QueueSink;
import netp.FlowTransmitter;
import netp.FlowReader;
import netp.PacketSource;
import netp.functions.flow.GetFlowSize;

/**
 * Displays the size of each arriving flow
 */
public class ReadFlowSize {
	
	public static void main(String[] args) throws ConnectorException {
		PacketSource source = new PacketSource("test.pcap");

		FlowTransmitter flow = new FlowTransmitter();
		try {
			Connector.connect(source, flow, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}
		
		FlowReader flowSize = new FlowReader(new GetFlowSize());
		try {
			Connector.connect(flow, flowSize, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}

		QueueSink sink = new QueueSink(1);
		try {
			Connector.connect(flowSize, sink, 0, 0);
		} catch (ConnectorException e) {
			e.printStackTrace();
		}
		
		// compute the first 15 packets
		for (int i = 0; i < 15; i++) {
			source.push();
			Integer output = (Integer) sink.remove()[0];
			System.out.println(i + ": " + output);
		}
	}
}

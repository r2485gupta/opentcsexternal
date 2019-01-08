package org.schoolbox.opentcs;

import org.opentcs.virtualvehicle.LoopbackCommunicationAdapter;

public interface AdaptorFactory {
	
	public LoopbackCommunicationAdapter getAdapter(String vehicleName);

}

package org.schoolbox.opentcs;

import org.opentcs.data.model.Vehicle;
import org.opentcs.virtualvehicle.LoopbackCommunicationAdapter;
import org.opentcs.virtualvehicle.VirtualVehicleConfiguration;

public abstract class AdaptorFactoryImpl implements AdaptorFactory {

	public LoopbackCommunicationAdapter getAdapter(String vehicleName) {
		return new LoopbackCommunicationAdapter(null, getVehicleConfiguration(), getVehicle(vehicleName));

	}

	private Vehicle getVehicle(String vehicleName) {
		return new Vehicle(vehicleName);
	}

	private VirtualVehicleConfiguration getVehicleConfiguration() {
		return new VirtualVehicleConfiguration() {

			public double simulationTimeFactor() {
				return 1;
			}

			public String rechargeOperation() {
				return "";
			}

			public boolean enable() {
				return true;
			}

			public int commandQueueCapacity() {
				return 1;
			}
		};

	}

	

}
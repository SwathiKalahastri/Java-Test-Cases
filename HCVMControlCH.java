/* 
 * This test was created by Swathi Kalahastri
 * to demo for healthcenter.api.vmcontrol.VMControl.ClassHistogram* functionality.
 */


package TestCase;


import com.ibm.java.diagnostics.healthcenter.api.ConnectionProperties;
import com.ibm.java.diagnostics.healthcenter.api.HealthCenter;
import com.ibm.java.diagnostics.healthcenter.api.HealthCenterException;
import com.ibm.java.diagnostics.healthcenter.api.HealthCenterJMXException;
import com.ibm.java.diagnostics.healthcenter.api.HealthCenterNotSupportedException;
import com.ibm.java.diagnostics.healthcenter.api.HealthCenterSSLException;
import com.ibm.java.diagnostics.healthcenter.api.classes.ClassHistogramData;
import com.ibm.java.diagnostics.healthcenter.api.classes.ClassHistogramEvent;
import com.ibm.java.diagnostics.healthcenter.api.classes.ClassHistogramEventListener;
import com.ibm.java.diagnostics.healthcenter.api.environment.EnvironmentData;
import com.ibm.java.diagnostics.healthcenter.api.factory.HealthCenterFactory;
import com.ibm.java.diagnostics.healthcenter.api.vmcontrol.VMControl;

public class HCVMControlCH {
public static void main(String[] args) throws HealthCenterJMXException, HealthCenterSSLException, HealthCenterException, InterruptedException {
		
		
		int retryTime = Integer.getInteger("healthCenterAPIFullConnectRetryTimeMilliseconds", 5000);
		int maxRetries = Integer.getInteger("healthCenterAPIMaxFullConnectRetries", 10);
		boolean hcEnvFlag = false;
		String destinationHost = "localhost";
		int destinationStartPort = 1972;
		boolean destinationScanAdditionalPorts = true;
		EnvironmentData environmentData = null;
		HealthCenter healthCenter;
		VMControl vmControl = null;
		String hcAgentVersion=null;
		ConnectionProperties conn1 = new ConnectionProperties(destinationHost, destinationStartPort);
		healthCenter = HealthCenterFactory.connect(conn1, destinationScanAdditionalPorts);
		environmentData = healthCenter.getEnvironmentData();
		vmControl = healthCenter.getVMControl();
		hcEnvFlag = false;
		
		
		for (int i = 0; i < maxRetries; i++) {
				hcAgentVersion = environmentData.getHealthCenterAgentVersion();
				if (hcAgentVersion != null) {
					hcEnvFlag = true;
					break;
				} else {
					Thread.sleep(retryTime);
				}
				
			}
		System.out.println("hcEnvFlag+++++++++++++++++++++++++++++++" +hcEnvFlag);
		
		hcEnvFlag = false;
		if (hcEnvFlag) {
	
		   healthCenter.getClassesData().addClassHistogramListener(new ClassHistogramEventListener() {
				@Override
				public void classHistogramEvent(ClassHistogramEvent event) {
					System.out.println("[" + new java.util.Date() + "] " + "Received class histogram event: "
							+ event.getEventTime() + " : ");
					for (ClassHistogramData data : event.getHistogramData()) {
						// System.out.println(data.getClassName() + " " + data.getClassCount() + " " +
						// data.getClassSize());
					}
				}
			});
		
		
		try {
			System.out.println("I'm calleding  vmControl.collectClassHistogramData() method +++++++++++++++++++++++++++++++");
			vmControl.collectClassHistogramData();

		} catch (HealthCenterNotSupportedException ex) {
			System.out.println("I am here in error+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			ex.printStackTrace();

     	}

	}else {
		 System.out.println("I've not reached the call vmControl.collectClassHistogramData() ++++++++++++++++++++++++++++++");
	}
	}

}
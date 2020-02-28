import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class Monitor {
		
	public static void main(String[] args) throws Exception {
		
		int mb = 1024 * 1024; 
		DecimalFormat df = new DecimalFormat("0.00");
		
		FileWriter csvWriter = new FileWriter("SystemMonitor_" + System.currentTimeMillis() + ".csv");
		
	    Runtime.getRuntime().addShutdownHook(new Thread() 
	    { 
	      public void run()
	      { 
	    	System.out.println("Application Terminating ..."); 
	        try {
				csvWriter.flush();
		        csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	      } 
	    }); 
				
		System.out.println("Starting system memory and cpu monitoring... ");
		System.out.println("---------------------------------------------");
		System.out.println("Updates every minute");
		System.out.println("---------------------------------------------");
		
		csvWriter.append("systemMemoryMb" +";"+"freeMemoryMb"+";"+"cpuLoad" + System.lineSeparator());
    	csvWriter.flush();
		
		while (true) {
					
					//recording every minute -> 60000 ms
					Thread.sleep(60000);

			    	long totalMemoryMb = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize() / mb;
			    	long freeMemoryMb = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize() / mb;
			    	double cpuLoad = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad();
			    	String cpuLoadRnd = df.format(cpuLoad);
			    	
			    	System.out.println("free memory: " + freeMemoryMb + " | CPU load: " + cpuLoadRnd);
			    	csvWriter.append(totalMemoryMb +";"+freeMemoryMb+";"+cpuLoadRnd + System.lineSeparator());
			    	csvWriter.flush();
			    	
			    }
	}
	
}

import java.util.Random;

class ProcessUDPPing {

	ProcessUDPPing(int packet_number) {
		
		System.out.println("Processing UDP Ping Packet Number " + packet_number);
		Random random = new Random();
		// Maximum processing time 1 second
		int process_time = random.nextInt(new PingParameters().max_processing_time);
		System.out.println(process_time);
		try {
			Thread.sleep(process_time); //Processing time
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(process_time > new PingParameters().TIMEOUT) {
			System.out.println("Processing of UDP Ping Packer Number " + packet_number + " failed!");
		} else {
			System.out.println("Processing UDP Ping Packet Number " + packet_number + " successful!");
		}
	}
	
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

class UDPPingClient {
	
	int TIMEOUT = 700; // Timeout of 700ms
	
	public static void main(String[] args) throws IOException {
	
		int echo_count = new PingParameters().echo_count;
		int server_port = new PingParameters().server_port;
		String server_name = new PingParameters().server_name;
		System.out.println("Client Started...");
		int lost_count = 0;
		int min_rtt = new PingParameters().max_processing_time, max_rtt = 0;
		float avg_rtt = 0;
		
		DatagramSocket client_socket = new DatagramSocket();
		InetAddress server_IP = InetAddress.getByName(server_name);
		
		System.out.println("Pinging " + server_name + " [" + server_IP + ":" + server_port + "]");
		while(echo_count > 0) {
			
			byte[] data_to_server = new byte[new PingParameters().size_of_data];
			byte[] data_from_server = new byte[new PingParameters().size_of_data];
			
			String ping_request = "Ping Request from ";
			data_to_server = ping_request.getBytes();
			
			DatagramPacket packet_to_server = new DatagramPacket(data_to_server, data_to_server.length, server_IP, server_port);
			client_socket.send(packet_to_server);
			long sent_time = new Date().getTime();
			
			DatagramPacket packet_from_server = new DatagramPacket(data_from_server, data_from_server.length);
			client_socket.receive(packet_from_server);
			long receive_time = new Date().getTime();
			int rtt = (int) (receive_time - sent_time);
			if(rtt > max_rtt) {
				max_rtt = rtt;
			}
			if(rtt < min_rtt) {
				min_rtt = rtt;
			}
			if(rtt > new PingParameters().TIMEOUT) {
				System.out.println("Request Timed Out!");
				lost_count ++;
			} else {
				avg_rtt = avg_rtt + rtt;
				System.out.println("Reply from [" + server_IP.getHostAddress() + ":" + server_port + "]: RTT = " + rtt + "ms");	
			}
			echo_count --;
		}
		float loss = (float) lost_count / new PingParameters().echo_count;
		avg_rtt = avg_rtt / (new PingParameters().echo_count - lost_count);
		System.out.println("\nUDP Ping Statistics");
		System.out.print("\tPackets: Sent = " + new PingParameters().echo_count + ", Received = " + (new PingParameters().echo_count - lost_count));
		System.out.print(", Lost = " + lost_count + "(" + loss + "% loss)");
		
		System.out.println("\nApproximate round trip times in milli-seconds");
		System.out.println("Minimum = " + min_rtt + "ms, Maximum = " + max_rtt + "ms, Average = " + avg_rtt + "ms");
		client_socket.close();
		
	}
}
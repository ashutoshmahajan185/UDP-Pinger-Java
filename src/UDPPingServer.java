import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class UDPPingServer {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Server Started...");

		int server_port = new PingParameters().server_port;
		int packet_number = 0;
		
		@SuppressWarnings("resource")
		DatagramSocket server_socket = new DatagramSocket(server_port);
		
		byte[] data_from_client = new byte[new PingParameters().size_of_data];
		byte[] data_to_client = new byte[new PingParameters().size_of_data];
		
		while(true) {
			
			DatagramPacket packet_from_client = new DatagramPacket(data_from_client, data_from_client.length);
			server_socket.receive(packet_from_client);
			
			InetAddress client_IP = packet_from_client.getAddress();
			int client_port = packet_from_client.getPort();
			
			String ping_response = "Ping Response";
			data_to_client = ping_response.getBytes();
			
			DatagramPacket server_data = new DatagramPacket(data_to_client, data_to_client.length, client_IP, client_port);
			packet_number ++;
			
			// This is used for the simulation of UDP Ping Echo Transmission and Reply
			ProcessUDPPing packet = new ProcessUDPPing(packet_number);
			
			server_socket.send(server_data);
			
		}
		
	}
	
}
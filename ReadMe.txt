Simple Implementation of UDP Pinger
Ashutosh Mahajan N15565485 abm523

This implementation uses simple Java Datagram Sockets and Packets. Time is recorded using the Date class.

Classes
	--> UDPPingServer: Defines socket and port of a Server. Accepts Ping requests from the Client. Processes them by calling ProcessUDPPing. Returns the result to Client.
	--> UDPPingClient: Defines the client and sends Ping requests to the Server. Records and calculates the RTTs, packet statistics.
		RTTs --> RTT for each successful packet, min RTT, max RTT, avg RTT.
		Packet Statistic --> Total Packets sent, Lost Packets, received Packets.
	--> ProcessUDPPing: Includes emulation of processing of the Ping requests packet by incorporating TIMEOUT and delays.
	--> PingParameters: Includes general parameters for UDP Ping

Steps for Execution
	--> Adjust the parameters in PingParameters as desired
	--> Compile and Run the UDPPingServer file
	--> Compile and Run the UDPPingClient file
	--> Output will be displayed in separate console windows
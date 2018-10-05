#include "tcpconnection.hpp"

#include <iostream>

using namespace T3;

using Poco::Net::StreamSocket;
using Poco::Net::TCPServerConnection;

TCPConnection::TCPConnection(const StreamSocket& socketStream) :
	TCPServerConnection{socketStream}
{

}

void TCPConnection::run() {
	std::cout << "New connection from: " << socket().peerAddress().host().toString() <<  std::endl;
	bool isOpen = true;
	Poco::Timespan timeOut(10,0);
	unsigned char incommingBuffer[1024];
	while(isOpen){
		if (socket().poll(timeOut,Poco::Net::Socket::SELECT_READ) == false){
			std::cout << "TIMEOUT!" << std::endl;
		} else {
			std::cout << "RX EVENT!!! ---> "   << std::endl;
			int nBytes = -1;

			try {
				nBytes = socket().receiveBytes(incommingBuffer, sizeof(incommingBuffer));
			}
			catch (Poco::Exception& exc) {
                    //Handle your network errors.
				std::cerr << "Network error: " << exc.displayText() << std::endl;
				isOpen = false;
			}


			if (nBytes==0){
				std::cout << "Client closes connection!" << std::endl;
				isOpen = false;
			}
			else{
				std::cout << "Receiving nBytes: " << nBytes << std::endl;
			}
		}
	}
	std::cout << "Connection finished!" << std::endl;
}
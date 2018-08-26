#include "requesthandler.hpp"

#include <thread>
using namespace T3;

RequestHandler::RequestHandler(const std::string& ipv4address, const Poco::UInt16 port) {

}

void RequestHandler::run() {
	while(m_runInteded) {
		// char* buffer[MAX_MESSAGE_SIZE];
		// m_socket->readBytes(buffer, MAX_MESSAGE_SIZE, true);
		std::this_thread::sleep(std::chrono::milliseconds(20));
	}
}
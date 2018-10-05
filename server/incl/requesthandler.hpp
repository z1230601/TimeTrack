#pragma once

#include <memory>

#include <Poco/Types.h>
#include <Poco/Net/ServerSocket.h>
#include <Poco/Net/TCPServer.h>
#include <Poco/Net/TCPServerParams.h>

namespace T3{
	class RequestHandler {
	public:
		RequestHandler(const Poco::UInt16 port);
		~RequestHandler();

		void run();

	private:
		Poco::UInt16 m_port;
		Poco::Net::ServerSocket m_socket;
		Poco::Net::TCPServerParams* m_serverParams;
		std::unique_ptr<Poco::Net::TCPServer> m_server;
		
	};	
}
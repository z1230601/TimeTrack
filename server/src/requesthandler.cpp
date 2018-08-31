#include "requesthandler.hpp"

#include <thread>

#include <Poco/Net/DatagramSocket.h>
#include <Poco/Net/SocketAddress.h>

#include "tcpconnection.hpp"

using namespace T3;

using Poco::Net::TCPServer;
using Poco::Net::TCPServerConnectionFactoryImpl;
using Poco::Net::TCPServerParams;

RequestHandler::RequestHandler(const Poco::UInt16 port) :
	m_port{port},
	m_socket{m_port},
	m_serverParams{new TCPServerParams()}
{	
    m_serverParams->setMaxThreads(4);
    m_serverParams->setMaxQueued(4);
    m_serverParams->setThreadIdleTime(100);
	m_server.reset(new TCPServer(new TCPServerConnectionFactoryImpl<TCPConnection>(), m_socket, m_serverParams));
}

RequestHandler::~RequestHandler() {
	m_server.reset();
}

void RequestHandler::run() {
    m_server->start();

    while(1); // refactor to wait on condition variable
}
#pragma once

#include <Poco/Net/TCPServerConnection.h>
#include <Poco/Net/StreamSocket.h>

namespace T3 {
	class TCPConnection : public Poco::Net::TCPServerConnection {
	public:
		TCPConnection(const Poco::Net::StreamSocket& socketStream);
		virtual ~TCPConnection() = default;

		void run() override;
	private:

	};
}
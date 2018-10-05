SET(server_SOURCES
	main.cpp
	requesthandler.cpp
	tcpconnection.cpp
)

include_directories(
	${CMAKE_SOURCE_DIR}/incl
	${Poco_INCLUDE_DIRS}
)

ADD_EXECUTABLE(t3server
	${server_SOURCES}
)

MESSAGE(STATUS "Linking ${Poco_LIBRARIES}")
TARGET_LINK_LIBRARIES(t3server
	${Poco_LIBRARIES}
	"/usr/lib/libPocoNet.so"
	"/usr/lib/libPocoJSON.so"
	"/usr/lib/libPocoUtil.so"
	"/usr/lib/libPocoXML.so"
	"/usr/lib/libPocoFoundation.so"
)
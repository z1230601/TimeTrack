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

MESSAGE(STATUS "Linking ${Poco_Libraries}")
TARGET_LINK_LIBRARIES(t3server
	${Poco_Libraries}
)
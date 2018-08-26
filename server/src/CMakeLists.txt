SET(server_SOURCES
	main.cpp
)



ADD_EXECUTABEL(t3server
	${server_SOURCES}
)

TARGET_LINK_LIBRARIES(t3server
	${Poco_Libraries})
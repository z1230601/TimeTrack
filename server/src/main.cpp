#include "requesthandler.hpp"


using namespace T3;

int main(int argc, char** argv) {
	RequestHandler handler{9101};


	handler.run();
	return 0;
}
OBJS = project2.o
CC = g++
DEBUG = -g
CFLAGS = -Wall -c
LFLAGS = -Wall $(DEBUG)

clean: rmoutput
	if [[ -d build ]]; then rm build/*.o; rm -r ./build; fi; 2>/dev/null

rmoutput:
	 if [[ -f "OutputArray.txt" ]]; then rm OutputArray.txt; fi; if [[ -f "Output.txt" ]]; then rm Output.txt; fi;

all: project movebuild

project: $(OBJS)
	$(CC) $(LFLAGS) $(OBJS) -o project

project2.o:
	$(CC) $(CFLAGS) project2.cpp

movebuild:
	mkdir -p ./build && mv *.o ./build && mv project build

run: rmoutput all
	./build/project
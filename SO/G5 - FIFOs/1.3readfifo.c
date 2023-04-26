#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFSIZE 1024

int main(int argc, char **argv) {
    int fifo = open("fifo", O_RDONLY);
    if (fifo < 0) {
        write(2, "Error opening fifo.\n", 21);
        exit(EXIT_FAILURE);
    }

    char buf[BUFSIZE]; int bytes;
    while ((bytes = read(fifo, buf, BUFSIZE)) > 0) {
        write(1, buf, bytes);
    }
}
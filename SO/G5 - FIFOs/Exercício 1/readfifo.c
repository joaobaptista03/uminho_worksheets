#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFSIZE 1024

int main(int argc, char **argv) {
    int fifo = open("fifo", O_RDONLY);
    if (fifo < 0) {
        perror("Error opening fifo!");
        exit(EXIT_FAILURE);
    }

    char buf[BUFSIZE]; int bytes;
    while ((bytes = read(fifo, buf, BUFSIZE)) > 0) {
        write(1, buf, bytes);
    }

    close(fifo);
    unlink("fifo");
}

#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>

#define BUFSIZE 1024

int main(int argc, char **argv) {
    if (mkfifo("fifo", 0666) < 0) {
        write(2, "Error creating fifo.\n", 22);
        exit(EXIT_FAILURE);
    }

    int file = open("test.txt", O_WRONLY | O_CREAT | O_TRUNC);
    if (file < 0) {
        write(2, "Error opening file.\n", 21);
        exit(EXIT_FAILURE);
    }
    
    int fifo = open("fifo", O_RDONLY);
    if (fifo < 0) {
        write(2, "Error opening fifo.\n", 21);
        exit(EXIT_FAILURE);
    }

    char buf[BUFSIZE]; int bytes;
    while ((bytes = read(fifo, buf, BUFSIZE)) > 0) {
        write(file, buf, bytes);
    }

    close(fifo);
    close(file);
}
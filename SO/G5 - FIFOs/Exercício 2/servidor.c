#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#define BUFSIZE 1024

int main(int argc, char **argv) {
    if (mkfifo("fifo", 0666) < 0) {
        perror("Error creating fifo!");
        exit(1);
    }

    int file = open("test.txt", O_WRONLY | O_CREAT | O_TRUNC);
    if (file < 0) {
        perror("Error opening file!");
        exit(1);
    }
    
    int fifo = open("fifo", O_RDONLY);
    if (fifo < 0) {
        perror("Error opening fifo!");
        exit(1);
    }

    char buf[BUFSIZE]; int bytes;
    while ((bytes = read(fifo, buf, BUFSIZE)) > 0) {
        write(file, buf, bytes);
    }

    close(fifo);
    close(file);
}
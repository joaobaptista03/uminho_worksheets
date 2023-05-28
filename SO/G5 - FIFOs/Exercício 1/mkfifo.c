#include <sys/stat.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
    if (mkfifo("fifo", 0666) < 0) {
        perror("Error creating fifo!");
        exit(EXIT_FAILURE);
    }
}

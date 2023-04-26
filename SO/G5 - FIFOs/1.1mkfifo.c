#include <sys/stat.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char **argv) {
    if (mkfifo("fifo", 0666) < 0) {
        write(2, "Error creating fifo.\n", 22);
        exit(EXIT_FAILURE);
    }
}
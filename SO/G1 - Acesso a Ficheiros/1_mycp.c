#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>

#define BUFFER 1024

int main(int argc, char* argv[]){
    char buffer[BUFFER];
    int charsRead;

    if (argc != 3) {
        perror("Usage: ./mycp <source> <dest>\n");
        exit(1);
    }

    int source = open(argv[1], O_RDONLY);
    if (source == -1) {
        perror("Couldn't open source file\n");
        exit(1);
    }

    int dest = open(argv[2], O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (dest == -1) {
        perror("Couldn't open destination file\n");
        close(source);
        exit(1);
    }

    while ((charsRead = read(source, buffer, BUFFER)) > 0)
        write(dest, buffer, charsRead);

    close(source);
    close(dest);
    return 0;
}
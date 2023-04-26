#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>

#define BUFSIZE 128

ssize_t readln(int fd, char *line, size_t size) {
    ssize_t size_r = 0;
    ssize_t temp;

    for (int i = 0; (temp = read(fd, line + i, 1) > 0) && (line[i] != '\n'); i++) size_r += temp;
    line[strlen(line) - 1] = '\0';

    return size_r;
}

int main(int argc, char **argv) {
    int fd = open("test.txt", O_RDONLY);
    if (fd < 0) {
        perror("Error opening test file.");
        return fd;
    }

    char *temp = malloc(BUFSIZE * sizeof(char));
    ssize_t bytesRead = readln(fd, temp, BUFSIZE);
    printf("Read %ld bytes/char's from the line \"%s\".\n", bytesRead, temp);
    free(temp);

    return 0;
}

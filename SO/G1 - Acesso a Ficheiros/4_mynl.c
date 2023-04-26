#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

#define BUFSIZE 1024

ssize_t readln(int fd, char *line, size_t size) {
    ssize_t size_r = 0;
    ssize_t temp;

    for (int i = 0; (temp = read(fd, line + i, 1) > 0) && (line[i] != '\n'); i++) 
        size_r += temp;

    line[strlen(line) - 1] = '\0';

    return size_r;
}

int main(int argc, char **argv) {
    int line_n = 1;
    char line[BUFSIZE] = "\0";
    ssize_t charsRead;

    for (charsRead = readln(1, line, BUFSIZE); charsRead > 0; charsRead = readln(1, line, BUFSIZE)) {
        printf("    %d -> %s (%ld char's read)\n", line_n, line, charsRead);
        memset(line, 0, BUFSIZE);
        line_n++;
    }

    return 0;
}

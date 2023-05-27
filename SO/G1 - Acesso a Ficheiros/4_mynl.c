#include <stdio.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>

#define BUFSIZE 1024

ssize_t readln(int fd, char *line, size_t size) {
  size_t current = 0;

  while (current < size) {
    ssize_t byte = read(fd, line + current, 1);

    if (byte != 1 || line[current] == '\n') {
      return current;
    }

    current++;
  }

  return current;
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
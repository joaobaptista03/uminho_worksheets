#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>

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
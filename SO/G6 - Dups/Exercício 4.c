#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int fd[2];
    pid_t pid;

    if (pipe(fd) == -1) {
        write(2, "Error creating pipe.\n", 22);
        exit(EXIT_FAILURE);
    }

    pid = fork();
    if (pid == -1) {
        write(2, "Error creating child process.\n", 31);
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        close(fd[1]);

        if (dup2(fd[0], STDIN_FILENO) == -1) {
            write(2, "Error redirecting stdin descriptor.\n", 37);
            exit(EXIT_FAILURE);
        }

        execlp("wc", "wc", NULL);
        
        write(2, "Error executing execlp.\n", 25);
        exit(EXIT_FAILURE);

    } else {
        char buffer[1024];
        int bytes_read;
        close(fd[0]);

        while ((bytes_read = read(STDIN_FILENO, buffer, sizeof(buffer))) > 0) {
            if (write(fd[1], buffer, bytes_read) == -1) {
                write(2, "Error writing in the pipe.\n", 28);
                exit(EXIT_FAILURE);
            }
        }

        close(fd[1]);

        wait(NULL);
    }

    return 0;
}

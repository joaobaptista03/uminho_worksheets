#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int fd[2];
    pid_t pid;

    if (pipe(fd) < 0) {
        perror("Error creating pipe!");
        exit(1);
    }

    pid = fork();
    if (pid < 0) {
        perror("Error creating child process!");
        exit(1);
    }

    if (pid == 0) {
        close(fd[1]);

        if (dup2(fd[0], STDIN_FILENO) < 0) {
            perror("Error redirecting stdin descriptor!");
            exit(1);
        }

        execlp("wc", "wc", NULL);
        
        perror("Error executing execlp.");
        exit(1);

    } else {
        char buffer[1024];
        int bytes_read;
        close(fd[0]);

        while ((bytes_read = read(STDIN_FILENO, buffer, sizeof(buffer))) > 0) {
            if (write(fd[1], buffer, bytes_read) < 0) {
                perror("Error writing in the pipe!");
                exit(1);
            }
        }

        close(fd[1]);

        wait(NULL);
    }

    return 0;
}

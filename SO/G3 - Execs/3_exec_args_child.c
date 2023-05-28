#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    for(int i = 1; i < argc; i++) {
        int fork_nr = fork();

        if (fork_nr < 0) {
            perror("Error creating child process!");
            exit(1);
        }

        if (fork_nr == 0) execlp(argv[i], argv[i], NULL);
    }

    for(int i = 1; i < argc; i++) {
        int status;
        wait(&status);
        printf("\nChild executed with exit code: %d\n", WEXITSTATUS(status));
    }
}
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

#define COMMAND_LENGTH 20

int main(int argc, char **argv){
    if (argc != 2) {
        perror("Invalid arguments. Usage: ./mysystem \"<command>\"");
        exit(1);
    }

    int nr_args = 0;

    char** command = malloc(sizeof(char) * COMMAND_LENGTH);
    command[0] = strtok(argv[1], " ");
    char *token = strtok(NULL, " ");

    while (token != NULL) {
        command = realloc(command, sizeof(char) * COMMAND_LENGTH * (++nr_args + 1));
        command[nr_args] = token;
        token = strtok(NULL, " ");
    }

    command[nr_args + 1] = NULL;

    int pid_child = fork();

    if (pid_child < 0) {
        perror("Error creating child process!");
        exit(1);
    }

    if (pid_child == 0) execvp(command[0], command);

    else {
        int status;
        wait(&status);
        printf("\nChild executed with exit code: %d\n", WEXITSTATUS(status));

        free(command);
    }

    return 0;
}
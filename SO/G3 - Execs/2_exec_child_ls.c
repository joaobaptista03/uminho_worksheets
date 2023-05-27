#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, const char *argv[]){
    pid_t pid = fork();
    int status;

    if (pid < 0) { 
        perror("Error creating child process.\n");
        exit(1);
    }

    else if (pid == 0) execlp("ls", "ls", "-l", NULL);

    else {
        wait(&status);
        printf("\nChild executed with exit code: %d\n", WEXITSTATUS(status));
    }

    return 0;
}
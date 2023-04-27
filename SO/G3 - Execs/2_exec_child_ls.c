#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, const char *argv[]){
    pid_t pid = fork();
    int status;
    int execRet;

    if (pid < 0) { 
        write(2, "Error creating child process.\n", 31);
        exit(EXIT_FAILURE);
    }

    else if (pid == 0) execlp("ls", "ls", "-l", NULL);

    else {
        wait(&status);
        char print[100]; sprintf(print, "\nChild executed with exit code: %d\n", WEXITSTATUS(status));
        write(1, print, strlen(print) + 1);
    }

    return 0;
}

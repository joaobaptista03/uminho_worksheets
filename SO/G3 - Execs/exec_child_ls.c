#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, const char *argv[]){
    pid_t pid = fork();
    int status;
    int execRet;

    if (pid < 0) 
        perror("Fork failed.");

    else if (pid == 0) {
        execRet = execlp("ls", "ls", "-l", NULL);

        if (execRet == -1) {
            printf("execlp failed\n");
        }
    }

    else {
        wait(&status);
        printf("\nChild executed with exit code: %d\n", WEXITSTATUS(status));
    }

    return 0;
}
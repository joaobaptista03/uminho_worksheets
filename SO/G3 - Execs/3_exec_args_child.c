#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
    int execRet;

    for(int i = 1; i < argc; i++) {
        if (fork() == 0) {
            execRet = execlp(argv[i], argv[i], NULL);    

            if (execRet == -1) {
                printf("execlp failed\n");
            }
        }
    }

    for(int i = 1; i <= argc; i++) {
        int status;
        wait(&status);
        if (WIFEXITED(status)) printf("\nChild executed with exit code: %d\n", WEXITSTATUS(status));
    }
}

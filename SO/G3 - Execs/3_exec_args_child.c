#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
    int execRet;

    for(int i = 1; i < argc; i++) {
        if (fork() == 0) execRet = execlp(argv[i], argv[i], NULL);
    }

    for(int i = 1; i <= argc; i++) {
        int status;
        wait(&status);
        char print[100]; sprintf(print, "\nChild executed with exit code: %d\n", WEXITSTATUS(status));
        write(1, print, strlen(print) + 1);
    }
}

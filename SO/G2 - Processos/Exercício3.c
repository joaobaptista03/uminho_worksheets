#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char **argv) {
    for(int i = 1; i <= 10; i++) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("Fork Failed!");
            exit(1);
        }
        
        else if (pid == 0) {
            printf("(FILHO) O meu PID é: %d\n", getpid());
            printf("(FILHO) O PID do meu pai é: %d\n", getppid());
            _exit(i);
        }
        else {
            int status;
            pid_t pid_filho = wait(&status);
            printf("(PAI) O meu processo filho %d terminou com código %d\n", pid_filho, WEXITSTATUS(status));
        }
    }

    return 0;
}
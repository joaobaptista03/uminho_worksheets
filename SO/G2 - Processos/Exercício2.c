#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, const char *argv[]){
    int pid = fork();
    
    if (pid < 0) {
        perror("Fork failed");
        exit(1);
    }
    
    else if (pid == 0) {
        printf("(FILHO) O meu PID é: %d\n", getpid());
        printf("(FILHO) O PID do meu pai é: %d\n", getppid());
    }
    
    else {
        printf("(PAI) O meu PID é: %d\n", getpid());
        printf("(PAI) O PID do meu filho é: %d\n", pid);
        printf("(PAI) O PID do meu pai é: %d\n", getppid());
    }
    return 0;
}
#include <unistd.h>
#include <stdio.h>

int main(int argc, char **argv) {
    int pd[2];
    pipe(pd);

    int f = fork();

    if (f < 0) { 
        perror("Fork failed!");
        return -1;
    }

    if (f == 0) {
        close(pd[0]);
        int enviar = 10;
        write(pd[1], &enviar, sizeof(int));
        printf("Sou o filho (PID -> %d) e enviei %ld bytes com o valor %d\n", getpid(), sizeof(enviar), enviar);
        _exit(0);
    }

    else {
        close(pd[1]);
        int recebido;
        while(read(pd[0], &recebido, sizeof(int)) > 0)
            printf("Sou o pai (PID -> %d) e recebi %ld bytes com o valor %d\n", getpid(), sizeof(recebido), recebido);
    }

    return 0;
}
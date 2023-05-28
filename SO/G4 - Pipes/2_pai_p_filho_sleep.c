#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int pd[2];
    pipe(pd);

    int f = fork();
        if (f < 0) { 
            write(2, "Error creating child process.\n", 31);
            exit(EXIT_FAILURE);
        }

    if (f == 0) {
        close(pd[1]);
        int recebido;
        read(pd[0], &recebido, sizeof(int));
        close(pd[0]);
        printf("Sou o filho (PID -> %d) e recebi %ld bytes com o valor %d\n", getpid(), sizeof(recebido), recebido);
        _exit(0);
    }

    else {
        close(pd[0]);
        int enviar = 10;
        sleep(5);
        write(pd[1], &enviar, sizeof(int));
        close(pd[1]);
        printf("Sou o pai (PID -> %d) e enviei %ld bytes com o valor %d\n", getpid(), sizeof(enviar), enviar);
    }

    return 0;
}
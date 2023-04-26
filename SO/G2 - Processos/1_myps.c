#include <stdio.h>
#include <unistd.h>

int main(int argc, const char *argv[]){
    printf("O meu identificador é: %d\n", getpid());
    printf("O identificador do meu pai é: %d\n", getppid());
    return 0;
}

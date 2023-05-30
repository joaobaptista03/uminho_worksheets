#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/wait.h>

#define BUFFSIZE 1024

int main(int argc, char **argv) {
    int file_stdin = open("etc/passwd", O_WRONLY | O_CREAT | O_TRUNC, 0666);
        if (file_stdin < 0) {
            write(2, "Error opening /etc/passwd.\n", 28);
            exit(EXIT_FAILURE);
        }
    if (dup2(file_stdin, 0) < 0) {
        write(2, "Error redirecting stdin descriptor.\n", 37);
        exit(EXIT_FAILURE);
    }

    int file_stdout = open("saida.txt", O_WRONLY | O_CREAT | O_TRUNC, 0666);
        if (file_stdout < 0) {
            write(2, "Error opening saida.txt.\n", 26);
            exit(EXIT_FAILURE);
        }
    if (dup2(file_stdout, 1) < 0) {
            write(2, "Error redirecting stdout descriptor.\n", 38);
            exit(EXIT_FAILURE);
    }

    int file_stderror = open("erros.txt", O_WRONLY | O_CREAT | O_TRUNC, 0666);
        if (file_stderror < 0) {
            write(2, "Error opening erros.txt.\n", 26);
            exit(EXIT_FAILURE);
        }
    if (dup2(file_stderror, 2) < 0) {
            write(2, "Error redirecting stderror descriptor.\n", 40);
            exit(EXIT_FAILURE);
    }

    int pid_fork = fork();
        if (pid_fork < 0) {
            write(2, "Error creating child process.\n", 31);
            exit(EXIT_FAILURE);
        }

    if (pid_fork == 0) {
        printf("Teste printf");
        perror("Teste perror");

        ssize_t bytes_read; char buf[BUFFSIZE];
        while ((bytes_read = read(0, buf, BUFFSIZE)) > 0) {
            puts(buf);
            perror(buf);
        }
    }

    else if (pid_fork > 0) wait(NULL);

    close(file_stdin);
    close(file_stdout);
    close(file_stderror);

    return 0;
}
#include <unistd.h>
#include <stdio.h>

int main(int argc, const char *argv[]) {
    //execl("/usr/bin/ls", "ls", "-l", NULL);
    //OU
    //execlp("ls", "ls", "-l", NULL);
    //OU
    char *command[] = {"ls", "-l", NULL};
    //execv("/usr/bin/ls", command);
    //OU
    execvp("ls", command);

    return 0;
}
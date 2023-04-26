#include <unistd.h>
#include <stdio.h>

int main(int argc, const char *argv[]) {
    //execl("/usr/bin/ls", "ls", "-l", NULL);
    
    //execlp("ls", "ls", "-l", NULL);

    char *command[] = {"ls", "-l", NULL};
    //execv("/usr/bin/ls", command);

    execvp("ls", command);

    return 0;
}
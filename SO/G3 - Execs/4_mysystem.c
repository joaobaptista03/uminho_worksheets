#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv){
    if (argc != 2) {
        write(2, "Invalid arguments. Usage: ./mysystem \"<command>\"", 49);
        exit(EXIT_FAILURE);
    }

    char *prog_name = strtok(argv[1], " ");

    char *arg_str = strtok(NULL, "");
    char **args_list = malloc(sizeof(char*)); args_list[0] = prog_name; 
    int num_args = 1;
    char *arg = strtok(arg_str, " ");
    while (arg != NULL) {
        num_args++;
        args_list = realloc(args_list, sizeof(char*) * num_args);
        args_list[num_args - 1] = strdup(arg);
        arg = strtok(NULL, " ");
    }
    args_list = realloc(args_list, sizeof(char*) * (num_args + 1));
    args_list[num_args] = NULL;

    execvp(prog_name, args_list);
}

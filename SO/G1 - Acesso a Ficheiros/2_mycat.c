#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char* argv[]){
    char input;

    if (argc != 1) {
        perror("Usage: ./mycat\n");
        exit(1);
    }

    while (read(0, &input, 1) > 0)
        write(1, &input, 1);
    
    return 0;
}
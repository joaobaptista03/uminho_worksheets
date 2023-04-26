#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char* argv[]){
    char input;

    if (argc != 1) {
        printf("Usage: ./mycat\n");
        return 0;
    }

    while (read(0, &input, 1) > 0)
        write(1, &input, 1);
    
    return 0;
}
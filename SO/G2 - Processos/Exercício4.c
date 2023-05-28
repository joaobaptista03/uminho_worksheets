#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int main(int argc, const char *argv[]){

	for (int i = 1; i <= 10; i ++) {
        pid_t pid = fork();
        if (fork < 0) {
            perror("Fork Failed");
			exit(1);
		}

		if (pid == 0) {
			printf("(FILHO %d) PID: %d.\n", i, getpid());
			_exit(i);
		}
	}

	for (int i = 0; i < 10; i++) {
		int status;
		pid_t child_pid = wait(&status);
		
		printf("(PAI) processo filho %d terminou com código %d.\n", child_pid, WEXITSTATUS(status));
	}
    return 0;
}
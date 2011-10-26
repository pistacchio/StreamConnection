#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>  


int getline(char line[])
{
  int nch = 0;
  int c;

  while((c = getchar()) != EOF)
	{
	  if(c == '\n') break;
    line[nch] = c;
    nch++;
	}

 if(c == EOF && nch == 0) return EOF;
 return nch;
}


main()
{
  srand(time(NULL));
  char line[20];

  while(getline(line) != EOF)
  {
    if (strcmp(line, "ready?") == 0) {printf("ready!\n"); fflush(stdout); }
    if (strcmp(line, "generate code") == 0)
    {
      char code[] = ".........................";
      int sharp = rand() % 24;
      code[sharp] = '#';

      char row[5];
      for (int i=0; i<5; i++)
      {
        strncpy(row, code+(i*5), 5);
        printf("%s\n", row);
        fflush(stdout);
      }
    }
    if (strcmp(line, "bye!") == 0) {printf("bye!\n"); fflush(stdout); return 0;}    
  }
  
  return 0;
}
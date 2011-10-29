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
  char line[20] = "";

  while(getline(line) != EOF)
  {
    if (strcmp(line, "ready?") == 0) {printf("ready!\n"); fflush(stdout); }
    if (strcmp(line, "generate code") == 0)
    {
      char code[] = ".........................";
      int sharp = rand() % 24;
      code[sharp] = '#';

      char row[6];
      row[5] = '\0'; // sets the last chat at 0 so that copying 5 chars with
                     //   strncpy results always as a null termined string
      
      for (int i=0; i<5; i++)
      {
        strncpy(row, code+(i*5), 5);
        printf("%s\n", row);
        fflush(stdout);
      }
    }
    if (strcmp(line, "find code") == 0)
    {
      char code[25];

      // makes a single string out of the 5 rows of code
      for (int i=0; i<5; i++)      
      {
        getline(line);
        strncpy(code+(i*5), line, 5);
      }
      int sharp = strcspn(code, "#");
      int y = sharp/5;
      int x = sharp-(y*5);
      printf("%d %d\n", x, y);
      fflush(stdout);            
    }
    if (strcmp(line, "bye!") == 0) {printf("bye!\n"); fflush(stdout); return 0;}
    
    memset(&line, '\0', 20); // resets line to all 0s
  }
  
  return 0;
}
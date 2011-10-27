#include <iostream>
#include <string>
#include <stdlib.h>
#include <vector>

using namespace std;

int main()
{
  string line;
  char cline[20];
  
  srand ( time(NULL) );
    
  while(cin.getline(cline, 20))
  {
    line = cline;
    
    if (line == "ready?") { cout << "ready!" << endl; cout.flush(); }
    if (line == "generate code")
    {
      string code = ".........................";
      int sharp = rand() % 24;
      code[sharp] = '#';

      for (int i=0; i<5; i++)
      {
        cout << code.substr(i*5, 5) << endl;
        fflush(stdout);
      }      
    }
    if (line == "find code")
    {
      string lines;
      string l;
      
      for (int i=0; i<5; i++)      
      {
        cin >> l;
        lines += l;
      }

      int sharp = int(lines.find('#'));
      int y = sharp/5;
      int x = sharp-(y*5);
      printf("%d %d\n", x, y);
      fflush(stdout);      
    }    
    if (line == "bye!") { cout << "bye!" << endl; cout.flush(); return 0; }
  }
  return 0;
}
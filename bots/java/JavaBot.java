import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaBot {
  public static void main(String[] args) throws Exception
  {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    String line;
    while ((line = in.readLine()) != null)
    {
      if (line.equals("ready?"))
      {
        System.out.println("ready!");
        System.out.flush();
      }
      if (line.equals("generate code"))
      {
        String code = ".........................";
        char[] cCode = code.toCharArray();
        cCode[new Random().nextInt(24)] = '#';
        for(int i = 0; i < 25; i++)
        {
          System.out.print(cCode[i]);
          if ((i+1) % 5 == 0)
          {
            System.out.print(System.getProperty("line.separator"));
            System.out.flush();
          }
        }
      }
      if (line.equals("find code"))
      {
        String code = "";
        for(int i = 0; i < 5; i++) { code += in.readLine(); }
        int sharp = code.indexOf('#');
        int y = sharp / 5;
        int x = sharp - (y * 5);
        System.out.printf("%d %d\n", x, y);
        System.out.flush();
      }
      if (line.equals("bye!"))
      {
        System.out.println("bye!");
        System.out.flush();
        System.exit(0);
      }
    }
  }
}

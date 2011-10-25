package org.github.pistacchio.streamcommunication;

import java.io.*;
import java.sql.Array;
import java.util.List;
import java.util.regex.Pattern;

public class Host {

  private static Bot bot1;
  private static Bot bot2;

  public static void main(String args[])
  {

    // checks that there are exactly 2 input arguments (bo1 and bot2)
    if (args.length != 2)
    {
      System.out.println("You must input two bots to make them communicate");
      System.exit(1);
    }

    // makes sure that no matter how the program exists, the subprocesses are killed
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void  run()
      {
        bot1.kill();
        bot2.kill();
      }
    });

    // loads up the bots!
    try
    {
      bot1 = new Bot(args[0]);
      bot2 = new Bot(args[1]);

      if (!bot1.run()) { System.out.println("Bot 1 could not be loaded"); System.exit(1); }
      if (!bot2.run()) { System.out.println("Bot 2 could not be loaded"); System.exit(1); }

      // checks that the bots are loaded and ready
      boolean ready1 = bot1.isReady();
      if (ready1) System.out.println("Bot 1 ready!");
      else { System.out.println("Bot 1 could not be initialized"); System.exit(1); }

      boolean ready2 = bot2.isReady();
      if (ready2) System.out.println("Bot 2 ready!");
      else { System.out.println("Bot 2 could not be initialized"); System.exit(1); }

      // generate code
      List<String> code = bot1.generateCode();
      if (code == null) { System.out.println("Bot 1 failed to generate the code"); System.exit(1); }

      String[] checkedCode = codeCheck(code);
      if (checkedCode == null) { System.out.println("Bot 1 generated a malformed code!"); System.exit(1); }

      System.out.println("Generated code:");
      for (String row : code)
        System.out.println(row);

      // evaluate code
      String[] foundCode = bot2.findCode(code);
      if (foundCode == null) { System.out.println("Bot 2 failed in finding the code"); System.exit(1); }
      if (!checkedCode[0].equals(foundCode[0]) || !checkedCode[1].equals(foundCode[1]))
      {
        System.out.println("Bot 2 found a wrong code");
        System.exit(1);
      }

      // goodbye!
      System.out.printf("Bot 2 found the right code [%s %s]!\n", foundCode[0], foundCode[1]);
      System.out.println("Waving bots goodbye...");

      if (!bot1.goodbye()) { System.out.println("Bot 1 failed to greet!"); System.exit(1); }
      if (!bot2.goodbye()) { System.out.println("Bot 2 failed to greet!"); System.exit(1); }

      System.out.println("Everything went as expected. Good job!");
      System.exit(0);

    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
      System.exit(1);
    }
  }

  /* given a code in the form of .....
   *                             .....
   *                             ....#
   *                             .....
   *                             .....
   * returns the position of [x, y] or null if the code is not in a correct form (5 x 5 and with 1 # and 24 dots)
   */
  private static String[] codeCheck(List<String> code)
  {
    if (code.size() != 5) return null;

    // check if there is 1 # and 24 .
    int dots = 0; int sharps = 0;
    for (String row : code)
    {
      for (int c = 0; c < row.length(); c++)
      {
        if (row.charAt(c) == '.') dots++;
        if (row.charAt(c) == '#') sharps++;
      }
    }

    if (dots != 24 || sharps != 1) return null;

    for (int y = 0; y < 5; y++)
    {
      if (code.get(y).length() != 5) return null;

      int x = code.get(y).indexOf('#');
      if (x != -1) return new String[] { Integer.toString(x), Integer.toString(y) };
    }

    return null;
  }

}

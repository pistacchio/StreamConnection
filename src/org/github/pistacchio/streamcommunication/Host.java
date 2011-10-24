package org.github.pistacchio.streamcommunication;

import java.io.*;
import java.util.*;

public class Host {

  private static List<Bot> botList;

  public static void main(String args[])
  {
    if (args.length != 2)
    {
      System.out.println("You must input two bots to make them communicate");
    }

    botList = new ArrayList<Bot>();

    // makes sure that no matter how the program exists, the subprocesses are killed
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void  run()
      {
        for (Bot bot : botList)
          bot.kill();
      }
    });

  // loads up the bots!
  for (String arg : args)
  {
    botList.add(new Bot(arg));

    try
    {
      for(Bot bot : botList)
      {
        bot.run();

        // checks that the boat is loaded and ready
        boolean ready = bot.isReady();

        if (!ready) System.exit(1);

        System.out.println("Bot " + (ready ? " ready" : " not ready"));

        List<String> code = bot.generateCode();

        if (code != null)
          for (String c : code)
            System.out.println(c);
      }
    }
    catch (Exception ex)
    {
      System.out.println(ex.getMessage());
      System.exit(1);
    }

  }
}

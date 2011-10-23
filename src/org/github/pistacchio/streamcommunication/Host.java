package org.github.pistacchio.streamcommunication;

import java.io.*;
import java.util.*;

public class Host {

    private static List<Bot> botList;

    public static void main(String args[])
    {
        botList = new ArrayList<Bot>();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void  run()
            {
                for (int i = 0; i < botList.size(); i++)
                {
                    Bot bot = botList.get(i);
                    bot.kill();
                }
            }
        });

        // loads up the bots!
        for (int i = 0; i < args.length; i++)
        {
            botList.add(new Bot(args[i]));
        }

        try
        {
            for (int i = 0; i < botList.size(); i++)
            {
                Bot bot = botList.get(i);
                bot.run();

                String ready = "";
                if (!bot.isReady()) ready = " not";
                System.out.println("Bot #" + i + ready + " ready.");
                
                List<String> code = bot.generateCode();

                for (int ii = 0; ii < code.size(); ii++)
                {
                    System.out.println(code.get(ii));
                }

            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            System.exit(1);
        }

    }
}

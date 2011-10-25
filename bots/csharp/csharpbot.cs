using System;
using System.Threading;
using System.Linq;
using System.Collections.Generic;

namespace org.github.pistacchio.streamcommunication.bots
{
    class CSharpBot
    {
        static void Main(string[] args)
        {
            while (true)
            {
                // sleeps 10 milliseconds not to eat out cpu time
                Thread.Sleep(10);
                String action = Console.ReadLine().Trim();

                switch (action)
                { 
                    case "ready?":
                        Console.WriteLine("ready!");
                        Console.Out.Flush();
                        break;
                    case "generate code":
                        // makes a list of 25 dots, converts one of them in a sharp, splits the string in
                        //  5 tokens of 5 characters each
                        List<char> row = new String('.', 25).ToCharArray().ToList();
                        row[new Random().Next(24)] = '#';
                        List<string> rows = new List<string>();                        
                        
                        for (int i = 0; i < 5; i++)
                            rows.Add(string.Join("", row.Select(c => c.ToString())
                                                        .Skip(i*5)
                                                        .Take(5)
                                                        .ToArray()));
                        foreach (string s in rows)
                        {
                            Console.WriteLine(s);
                            Console.Out.Flush();
                        }
                        break;
                    case "find code":
                        List<string> code = new List<string>();
                        for (int i = 0; i < 5; i++)
                            code.Add(Console.ReadLine());
                        for (int y = 0; y < 5; y++)
                        {
                            int x = code[y].IndexOf('#');
                            if (x != -1)
                            {
                                Console.WriteLine("{0} {1}", x, y);
                                Console.Out.Flush();
                            }
                        }
                        break;
                    case "bye!":
                                Console.WriteLine("bye!");
                                Console.Out.Flush();
                                System.Environment.Exit(0);
                        break;
                }

            }
        }
    }
}

package org.github.pistacchio.streamcommunication;

/**
 Bot.java
 implements a runnable bot by running an external program passed as a string in the constructor
 **/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot
{
  private String command;
  private ProcessBuilder processBuilder;
  private Process process;
  private BufferedReader output;
  private BufferedWriter input;

  // extends BufferedWriter by adding writeln, that writes to the buffer appending a line break and flushing the stream
  class BufferedWriter extends java.io.BufferedWriter
  {
    public BufferedWriter(OutputStreamWriter outputStreamWriter)
    {
      super(outputStreamWriter);
    }

    public void writeln(String s)
    {
      try
      {
        super.write(s + "\n");
        super.flush();
      }
      catch (IOException ex) {}
    }
  }

  public Bot(String command)
  {
    this.command = command;

    // since ProcessBuilder accepts a list of parameters (command + args), splits command into tokens
    this.processBuilder = new ProcessBuilder(this.getCommand());
  }

  // splits a string by spaces
  private List<String> getCommand()
  {
    return Arrays.asList(this.command.split(" "));
  }

  public boolean run()
  {
    try
    {
      this.process = this.processBuilder.start();

      this.output = new WaitingBufferedReader(new InputStreamReader(this.process.getInputStream()), 3000);
      this.input = new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream()));

      return true;
    }
    catch(IOException ex)
    {
      return false;
    }
  }

  // asks the bot to generate a new "code" (a 5 x 5 grid of '.' with one randomly placed '#') and returns such code
  public List<String> generateCode()
  {
    try
    {
      List<String> code = new ArrayList<String>();

      this.input.writeln("generate code");
      for (int i = 0; i < 5; i++)
      {
        String c = this.output.readLine();
        if (c != null)
          code.add(c);
        else
          throw new IOException();
      }

      return code;
    }
    catch (IOException ex)
    {
      return null;
    }
  }

  // provides the bot with a five rows code and asks it to find the position of '#'
  public String[] findCode(List<String> code)
  {
    try
    {
      this.input.writeln("find code");
      for (String row : code)
        this.input.writeln(row);

      String foundCodeRaw = this.output.readLine();
      String[] foundCode = foundCodeRaw.split(" ");
      if (foundCode.length != 2) return null;

      return foundCode;
    }
    catch (IOException ex)
    {
      return null;
    }
  }

  // asks the bot if it's ready
  public boolean isReady()
  {
      try
      {
        this.input.writeln("ready?");

        String out = this.output.readLine();
        if (out != null)
          return (out.equals("ready!"));
        else
          return false;
      }
      catch (IOException ex)
      {
        return false;
      }
  }

  // sends 'bye!' and expects 'bye!'
  public boolean goodbye()
  {
    this.input.writeln("bye!");
    try { return this.output.readLine().equals("bye!"); } catch (IOException ex) { return false;}
  }

  public void kill()
  {
    try { this.process.destroy(); } catch (Exception ex) {}
  }
}

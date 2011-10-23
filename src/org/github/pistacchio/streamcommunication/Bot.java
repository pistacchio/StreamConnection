package org.github.pistacchio.streamcommunication;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Bot
{
    private String command;
    private ProcessBuilder processBuilder;
    private Process process;
    private BufferedReader output;
    private BufferedWriter input;


    public Bot(String command)
    {
        this.command = command;
        this.processBuilder = new ProcessBuilder(this.getCommand());
    }

    private List<String> getCommand()
    {
        return Arrays.asList(this.command.split(" "));
    }

    public boolean run()
    {
        try
        {
            this.process = this.processBuilder.start();

            this.output = new BufferedReader(new InputStreamReader(this.process.getInputStream()));
            this.input = new BufferedWriter(new OutputStreamWriter(this.process.getOutputStream()));

            return true;
        }
        catch(IOException ex)
        {
            return false;
        }
    }

    public boolean isReady()
    {
        try
        {
            this.input.write("ready?");
            this.input.newLine();
            this.input.flush();

            String out = this.output.readLine();
            return (out.equals("ready!"));
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    /*
    public String tell(String msg)
    {
        this.input.write(msg);

    }
    */
}

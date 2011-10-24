package org.github.pistacchio.streamcommunication;
/**
 * Extends BufferedReader.readLine by adding a maximum waiting time.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaitingBufferedReader extends BufferedReader
{
  private long waitingTime;

  private String message;
  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }

  public WaitingBufferedReader(InputStreamReader in, long waitingTime)
  {
    super(in);
    this.waitingTime = waitingTime;
  }

  @Override
  public String readLine()
  {
    long startTime = System.currentTimeMillis();
    setMessage(null);

    Thread thread = new Thread()
    {
      public void run()
      {
        try {
          String m = WaitingBufferedReader.super.readLine();
          WaitingBufferedReader.this.setMessage(m);
        } catch (IOException e) {
          setMessage(null);
        }
      }
    };

    thread.start();

    try {
      synchronized (thread){ thread.wait(waitingTime); }
    } catch (InterruptedException e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

      if (this.getMessage() != null)
        return this.getMessage();

    return null;
  }
}

package org.schoolbox.opentcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Vehicle implements Runnable {

  private ServerSocket server;

  public Vehicle(final Integer portNumber) {
    try {
      this.server = new ServerSocket(portNumber);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    try {
      try {
        while (true) {
          final Socket client = this.server.accept();
          final BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
          final PrintWriter w = new PrintWriter(client.getOutputStream(), true);
          w.println("Vehicle Started. Type 'bye' to close.");
          String line;
          do {
            line = r.readLine();
            if (line != null)
              w.println("Got: " + line);
          } while (!line.trim().equals("bye"));
          client.close();
        }
      } catch (final Exception err) {
        System.err.println(err);
      }

    } catch (final Exception e) {
      System.out.println("Exception in tcp server");
    }
  }
}

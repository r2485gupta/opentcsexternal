package org.schoolbox.opentcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

  private ServerSocket server;

  public TcpServer(final int portnum) {
    try {
      this.server = new ServerSocket(portnum);
    } catch (final Exception err) {
      System.out.println(err);
    }
  }

  public void serve() {
    try {
      while (true) {
        final Socket client = this.server.accept();
        final BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
        final PrintWriter w = new PrintWriter(client.getOutputStream(), true);
        w.println("Welcome to the Java EchoServer.  Type 'bye' to close.");
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
  }

  public static void main(final String[] args) {
    Integer initialPort = 10000;
    for (Integer i = 0; i < 5; i++) {
      final Vehicle r = new Vehicle(initialPort++);
      new Thread(r).start();
    }

  }

}
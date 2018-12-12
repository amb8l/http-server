package uk.ashleybye.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpPort implements Port {

  private int port;
  private ServerSocket serverSocket;

  public HttpPort(int port) {
    this.port = port;
  }

  public void listen() {
    try {
      serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public Connection acceptConnection() {
    try {
      Socket socket = serverSocket.accept();
      return new HttpConnection(socket);
    } catch (IOException e) {
      throw new PortUnavailableException();
    }
  }

  @Override
  public void close() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      throw new ClosingPortException();
    }
  }
}

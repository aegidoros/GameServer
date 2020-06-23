package com.aer.backend.httpserver;

import com.aer.backend.controller.GameController;
import com.aer.backend.exception.BackEndException;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main Class where the HttpServer for the BackEnd is deployed.
 *
 * @author Alberto Egido
 * @version 1.0
 * @date 22/05/20
 */
public class BackEndServer {

  public static int PORT = 8081;

  /**
   * Main Method where the HttpServer is deployed
   * The HttpPort can be change, running the app with the argument [-p portNumber]
   *
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    //validating the Java Arguments
    if (args.length > 0) {
      try {
        if (args.length == 2) {
          if (args[0].equals("-p")) {
            PORT = Integer.parseInt(args[1]);
          } else {
            throw new BackEndException("Invalid argument '" + args[0] + "'.");
          }
        } else {
          throw new BackEndException("Invalid number of arguments.");
        }
      } catch (Exception e) {
        System.err.println("Error with the arguments.");
        System.err.println(e.getMessage());
        System.err.println("java -jar KingGameServer.jar [-p portNumber]");
        return;
      }
    }
    try {
      System.out.println("\n\n   Starting HTTPServer.");
      String hostName = "localhost";
      try {
        hostName = InetAddress.getLocalHost().getCanonicalHostName();
      } catch (UnknownHostException ex) {
        System.err.println("Unknown Host: " + ex);
      }
      HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
      HttpContext httpContext = httpServer.createContext("/", new BackEndHttpHandler(GameController.getInstance()));
      httpContext.getFilters().add(new BackEndHttpFilter());
      ExecutorService executorService = Executors.newCachedThreadPool();
      httpServer.setExecutor(executorService);
      httpServer.start();
      System.out.println("   HTTPServer started in http://" + hostName + ":" + PORT + "/");
      System.out.println("   Started HTTPServer Successfully!\n");
    } catch (Exception e) {
      System.err.println("Error with the HTTPServer.");
      System.err.println(e.getMessage());
      //            e.printStackTrace();
      //            throw new BackEndException(e);
    }
  }
}

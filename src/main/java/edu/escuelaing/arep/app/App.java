package edu.escuelaing.arep.app;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        HttpServer server = new HttpServer();
        HttpHandler.set("/hola","Buenos dias.");
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

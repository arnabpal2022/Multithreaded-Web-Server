package org.practice.requests;

import java.io.*;
import java.net.*;
import java.util.*;

public class WebServer {
    private ServerSocket serverSocket;

    public WebServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() {
        System.out.println("Web server started on port " + serverSocket.getLocalPort());

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Read request from client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = reader.readLine();
                System.out.println("Request: " + request);

                // Send response back to client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/html");
                writer.println();
                writer.println("<html><body>Hello, World!</body></html>");

                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Error handling client: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        WebServer server = new WebServer(8000);
        server.start();
    }
}

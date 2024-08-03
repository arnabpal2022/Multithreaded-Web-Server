package org.multithreadedServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class Server {
    private ServerSocket serverSocket;
    private final ExecutorService threadPool;

    public Server(int poolSize) {
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void start(int port) throws IOException {
        Thread connectionThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server is listening on port " + port);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    if (clientSocket != null) {
                        try {
                            threadPool.execute(() -> handleClient(clientSocket));
                        } catch (RejectedExecutionException e) {
                            System.err.println("Failed to execute task: " + e.getMessage());
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                                System.err.println("Failed to close client socket: " + ex.getMessage());
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error accepting client connections: " + e.getMessage());
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        System.err.println("Failed to close server socket: " + e.getMessage());
                    }
                }
            }
        });

        connectionThread.start();
    };

    public void stop() throws IOException {
        serverSocket.close();
    };

    public void handleClient(Socket clientSocket){
        try {
            RequestHandler r = new RequestHandlerFactory().createHandler();
            r.handleRequest(clientSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Failed to close client socket: " + e.getMessage());
                }
            }
        }
    }
}

package org.multithreadedServer;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Server server = new Server(10);
        server.start(8080);
    }
}

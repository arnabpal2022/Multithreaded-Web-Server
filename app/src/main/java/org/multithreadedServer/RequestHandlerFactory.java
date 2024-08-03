package org.multithreadedServer;

public class RequestHandlerFactory {
    public RequestHandler createHandler(){
        return new RequestHandler();
    }
}

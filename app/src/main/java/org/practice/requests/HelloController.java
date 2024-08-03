package org.practice.requests;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@RestController
public class HelloController {

    @GetMapping("/")
    public String hello(HttpServletRequest request) throws IOException {
        // Get the request method
        String method = request.getMethod();
        System.out.println("Request Method: " + method);

        // Get the request URL
        String url = request.getRequestURL().toString();
        System.out.println("Request URL: " + url);

        // Get the request headers
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println("Header: " + headerName + " = " + headerValue);
        }

        // Get the request parameters
        Map<String, String[]> parameters = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            System.out.println("Parameter: " + entry.getKey() + " = " + Arrays.toString(entry.getValue()));
        }

        // Get the request body
        String body = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Request Body: " + body);

        return "Hello, World!";
    }

    @PostMapping("/post")
    public String post(@RequestBody String body) {
        System.out.println("Request Body: " + body);
        return "Hello, World!";
    }

    @GetMapping("/query")
    public String query(@RequestParam(name = "name") String name) {
        System.out.println("Query Parameter: name = " + name);
        return "Hello, " + name + "!";
    }
}
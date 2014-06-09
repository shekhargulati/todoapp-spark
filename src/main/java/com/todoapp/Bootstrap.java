package com.todoapp;

import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/**
 * Created by shekhargulati on 09/06/14.
 */
public class Bootstrap {
    private static final String API_CONTEXT = "/api/v1";
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "localhost";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_IP") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_IP")) : 8080;

    public static void main(String[] args) {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello, World!!";
            }
        });

        post(API_CONTEXT + "/todos", "application/json", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return null;
            }
        });

        get(API_CONTEXT + "/todos/:id", "application/json", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "Learn Spark";
            }
        });


    }
}

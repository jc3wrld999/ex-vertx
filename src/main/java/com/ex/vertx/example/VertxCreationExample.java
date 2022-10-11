package com.ex.vertx.example;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VertxCreationExample {
    private Vertx vertx = Vertx.vertx();

    public void instanceCreation() {
        System.out.println("Vert.x instance : " + vertx);
    }

    public void httpServerCreation() {
        vertx.createHttpServer()
            .requestHandler(req -> {
                req.response().end("hello !");
            }).listen(8080);
    }

    public void producingJson() {
        vertx.createHttpServer()
        .requestHandler(req -> {
            JsonObject json = new JsonObject()
                .put("message", "hello");
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        })
        .listen(8080);
    }

    public void retrievingQueryParameters() {
        vertx.createHttpServer()
        .requestHandler(req -> {
            String name = req.getParam("name");
            String message = "hello " + (name != null && ! name.trim().isEmpty() ? name : "world") + "!";
            JsonObject json = new JsonObject()
                .put("message", message);
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        });        
    }

    public void observingTheEventLoop() {
        vertx.createHttpServer()
        .requestHandler(req -> {
            String name = req.getParam("name");
            String message = "hello " + (name != null ? name : "world") + "!";
            JsonObject json = new JsonObject()
                .put("message", message)
                .put("time", System.currentTimeMillis())
                .put("thread", Thread.currentThread().getName());
           req.response()
                .putHeader("Content-Type", "application/json; charset=UTF8")
                .end(json.encodePrettily());
        })
        .listen(8080);        
    }
}

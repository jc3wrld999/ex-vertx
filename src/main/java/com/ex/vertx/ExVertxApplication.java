package com.ex.vertx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

@SpringBootApplication
public class ExVertxApplication extends AbstractVerticle {

	public static void main(String[] args) {
		SpringApplication.run(ExVertxApplication.class, args);

		Vertx vertx = Vertx.vertx();
		
		vertx.createHttpServer().requestHandler(req -> {
			req.response()
			  .putHeader("content-type", "text/plain")
			  .end("Hello from Vert.x!");
		  }).listen(8888, http -> {
			if (http.succeeded()) {
			  System.out.println("HTTP server started on port 8888");
			} else {
			}
		  });
	}


}

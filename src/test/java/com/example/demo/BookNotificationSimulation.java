package com.example.demo;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BookNotificationSimulation extends Simulation {

    // Define HTTP Protocol
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080") // Base URL for the application
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Define Scenario
    ScenarioBuilder scn = scenario("Book Notification Test")
            .exec(http("Add New Book")
                    .post("/api/v1/books/add")
                    .body(StringBody("{ \"id\": null, \"title\": \"Gatling Java Test\", \"author\": \"John Doe\", \"isbn\": \"123-4567891234\", \"publisher\": \"Gatling Press\", \"pages\": 200 }"))
                    .check(status().is(200)));

    {
        // Load Simulation
        setUp(
                scn.injectOpen(atOnceUsers(400)) // Simulate 10 users at once
        ).protocols(httpProtocol);
    }
}

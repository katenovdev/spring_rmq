import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class RabbitControllerSimulation extends Simulation {

  // Define the HTTP protocol
  val httpProtocol = http
    .baseUrl("http://localhost:8080") // Change to your Spring Boot app's base URL
    .acceptHeader("application/json")

  // Define the scenario
  val scn = scenario("RabbitController Load Test")
    .exec(
      http("Send Message")
        .get("/send") // Endpoint to hit
        .queryParam("message", "Hello, RabbitMQ!") // Add query parameters
        .check(status.is(200)) // Verify response status
    )

  // Set up the load test
  setUp(
    scn.inject(
      rampUsers(100).during(10.seconds) // Simulate 100 users over 10 seconds
    )
  ).protocols(httpProtocol)
}
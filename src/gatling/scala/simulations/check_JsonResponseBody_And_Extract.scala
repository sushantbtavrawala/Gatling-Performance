package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

import baseConfig.BaseSimulation
class check_JsonResponseBody_And_Extract extends BaseSimulation {

  val scn = scenario("Video Game DB")
    .exec(http("Get specific game")
    .get("videogames/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpConf)
}

package simulations
import baseConfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt
class checkResponseCode extends BaseSimulation{

  val scn = scenario("Video Game DB")

    .exec(http("Get all Video Game - 1st Call")
    .get("videogames")
    .check(status.is(200)))

    .exec(http("Get specific game")
      .get("videogames/1")
      .check(status.in(200 to 210)))

    .exec(http("Get all Video Game - 2nd Call")
      .get("videogames")
      .check(status.not(404),status.not(500)))

  setUp(
    scn.inject(atOnceUsers(1)))
      .protocols(httpConf)

}

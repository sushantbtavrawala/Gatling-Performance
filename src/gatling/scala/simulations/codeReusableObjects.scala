package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

import baseConfig.BaseSimulation

class codeReusableObjects extends BaseSimulation
{
    def getAllVideoGames() = {
    exec(http("Get all Video Game")
    .get("videogames")
    .check(status.is(200)))
    }

    def getSpecificVideoGame() = {
      exec(http("Get specific game")
        .get("videogames/1")
        .check(status.in(200 to 210)))
    }

    val scn = scenario("video game DB")
        .exec(getAllVideoGames() )
        .pause(5)
        .exec(getSpecificVideoGame())
        .pause(5)
        .exec(getAllVideoGames())

  setUp(
    scn.inject(atOnceUsers(1)))
    .protocols(httpConf)
}

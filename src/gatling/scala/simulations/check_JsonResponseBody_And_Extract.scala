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

    //Save game ID to the variable
    .exec(http("Get all video games")
      .get("videogames")
      .check(jsonPath("$[1].id").saveAs("gameID")))
      .exec {session => println(session); session}

    //Check the value with Json Path
      .exec(http("Get specific game with parameter")
      .get("videogames/${gameID}")
      .check(jsonPath("$.name").is("Gran Turismo 3"))
    //Capture response body variable & Print
      .check(bodyString.saveAs("responseBody")))
      .exec{session => println(session("responseBody").as[String]); session}

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(httpConf)
}

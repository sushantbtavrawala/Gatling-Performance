import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder
import simulations.{addPauseTime, checkResponseCode, check_JsonResponseBody_And_Extract, codeReusableObjects}

object MyGatlingRunner {

  def main(args: Array[String]): Unit = {

    val simClass = classOf[codeReusableObjects].getName
    val props = new GatlingPropertiesBuilder
    props.simulationClass(simClass)

    Gatling.fromMap(props.build)
  }

}

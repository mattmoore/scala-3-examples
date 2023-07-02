//> using scala "3.3.0"
//> using dep "com.github.pureconfig::pureconfig-core:0.17.4"

import pureconfig.*
import pureconfig.generic.derivation.default.*

case class KinesisConfig(endpoint: Option[String]) derives ConfigReader

@main
def main =
  val config = ConfigSource
    .string(
      """
      endpoint = ${?KINESIS_ENDPOINT}
      """
    )
    .load[KinesisConfig]
  println(config)

  val config2 = ConfigSource
    .string(
      """
      endpoint = "http://localhost:4566"
      """
    )
    .load[KinesisConfig]
  println(config2)

  val config3 = ConfigSource
    .string(
      """
      endpoint = "http://localhost:4566"
      endpoint = ${?KINESIS_ENDPOINT}
      """
    )
    .load[KinesisConfig]
  println(config2)

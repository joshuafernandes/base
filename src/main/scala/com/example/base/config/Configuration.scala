package com.example.base.config

import com.typesafe.config.Config

trait Configuration {

  val ns = "com.example.base"
  val config: Config

  lazy val serviceHost: String = config.getString(s"$ns.host")
  lazy val servicePort: Int = config.getInt(s"$ns.port")
  lazy val environment: String = config.getString(s"$ns.environment")


  lazy val baseConfig = BaseConfig(serviceHost, servicePort, environment)

}

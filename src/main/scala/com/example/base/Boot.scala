package com.example.base

import akka.actor.{ActorSystem, Props}
import akka.event.Logging
import akka.io.IO
import com.example.base.service.BaseActor
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import spray.servlet.WebBoot

import scala.concurrent.duration._
import com.example.base.config.Configuration
import com.typesafe.config.ConfigFactory

class Boot extends WebBoot with Configuration {
  override val config = ConfigFactory.defaultApplication
  implicit val system = ActorSystem("base")
  val log = Logging.getLogger(system, this)
  log.info(s"Base config: $config")

  val props = Props.create(classOf[BaseActor], baseConfig)
  val serviceActor = system.actorOf(props)
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(serviceActor, interface = serviceHost, port = servicePort)

}

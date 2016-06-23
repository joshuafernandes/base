package com.example.base
package service

import akka.actor.{Actor, ActorContext, ActorSystem}
import com.example.base.config.BaseConfig
import org.slf4j.{LoggerFactory, Logger}
import spray.routing._

import scala.concurrent.ExecutionContextExecutor

class BaseActor(val baseConfig: BaseConfig) extends Actor with BaseService  {
  implicit val actorSys: ActorSystem = context.system
  def actorRefFactory: ActorContext = context
  val logger = LoggerFactory.getLogger(classOf[BaseActor])
  def receive = runRoute(basicRoutes)
  implicit val executionContext: ExecutionContextExecutor = actorRefFactory.dispatcher
}

trait BaseService extends HttpService {
  val baseConfig: BaseConfig

  val basicRoutes: Route = {
    compressResponseIfRequested() {
      requestUri { reqUri =>
          path("version") {
            get {
              complete("1.0.0")
            }
          } ~
        pathPrefix("1") {
          path("env") {
            get {
              //read based on config and display the value
              complete(baseConfig.environment)
            }
          }
        }

      }
    }
  }
}

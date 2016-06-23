package com.example.base
package service

import com.example.base.config.{BaseConfig, Configuration}
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory
import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import spray.testkit.Specs2RouteTest
import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

trait ServiceTestBase extends Specification
                      with Specs2RouteTest
                      with Configuration
                      with BaseService
                      with Mockito {

  override def testConfig = ConfigFactory.load("test.conf")
  override val config = testConfig
  args(sequential = true) //makes test execution sequential

  def actorRefFactory = system
  implicit val actorSys = system
  implicit val executionContext: ExecutionContextExecutor = executor
  implicit val routeTestTimeout = RouteTestTimeout(FiniteDuration(60, SECONDS))
  val logger = LoggerFactory.getLogger(classOf[BaseActor])
  lazy val apiVersion = "1"
  override lazy val baseConfig: BaseConfig = BaseConfig(serviceHost, servicePort, environment)


}
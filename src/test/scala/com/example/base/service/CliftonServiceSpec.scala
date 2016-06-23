package com.example.base
package service

import com.example.base.service.ServiceTestBase
import spray.http._
import spray.http.StatusCodes._
import spray.http.HttpMethods._

class BaseServiceSpec extends ServiceTestBase {


  def createRequest(method: HttpMethod, uri: Uri, headers: List[HttpHeader] = List[HttpHeader](), entity: HttpEntity=HttpData.Empty) =
    HttpRequest(
      method = method,
      uri = uri,
      headers = headers,
      entity = entity,
      protocol = HttpProtocols.`HTTP/1.1`
    )


  "Base Service" should {

    "GETs to /version" should {
      "return the current version" in {
        val req = createRequest(GET, "/version")
        req ~> basicRoutes ~> check {
          status must beEqualTo(OK)
          response.entity.asString must contain("1.0.0")
        }
      }
    }

    "GETs to /1/env" should {
      "return the env string" in {
        val req = createRequest(GET, "/1/env")
        req ~> basicRoutes ~> check {
          status must beEqualTo(OK)
          response.entity.asString must contain("test")
        }
      }
    }


  }

}
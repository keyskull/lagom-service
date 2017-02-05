package me.keyskull.lagom

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * Created by keyskull on 2017/1/24.
  */
trait AuthService extends Service {
  Firebase
  def checkUser: ServiceCall[String, String]

  override def descriptor = {
    import Service._
    import com.lightbend.lagom.scaladsl.api.transport.Method
    named("user").withCalls(
      restCall(Method.GET,"/user/:userName", checkUser _)
    )
  }
}

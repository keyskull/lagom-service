package me.keyskull.user

import java.util.UUID

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.Service.pathCall
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

/**
  * Created by keyskull on 2017/1/24.
  */
trait UserService extends Service {
  def checkUser: ServiceCall[String, String]

  def createUser: ServiceCall[CreateUser, NotUsed]

  def getUser(userId: UUID): ServiceCall[NotUsed, User]

  override def descriptor = {
    import Service._
    import com.lightbend.lagom.scaladsl.api.transport.Method
    named("user").withCalls(
      restCall(Method.GET, "/user/:userName", checkUser _),
      pathCall("/api/user", createUser),
      pathCall("/api/user/:id", getUser _)
    )
  }
}

case class User(id: UUID, name: String)

object User {
  implicit val format: Format[User] = Json.format
}

case class CreateUser(name: String)

object CreateUser {
  implicit val format: Format[CreateUser] = Json.format
}
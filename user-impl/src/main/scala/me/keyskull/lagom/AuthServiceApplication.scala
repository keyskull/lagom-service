package me.keyskull.lagom

/**
  * Created by keyskull on 2017/1/24.
  */

import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomServer}
import com.softwaremill.macwire._
import me.keyskull.user.{AuthCommand,UserService}
import play.api.libs.ws.ahc.AhcWSComponents

abstract class AuthServiceApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents
    with CassandraPersistenceComponents {

  override lazy val lagomServer = LagomServer.forServices(
    bindService[UserService].to(wire[UserServiceImpl])
  )
  override def jsonSerializerRegistry = AuthCommand

  persistentEntityRegistry.register(wire[UserEntity])

}
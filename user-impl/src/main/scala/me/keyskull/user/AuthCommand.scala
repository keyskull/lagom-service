package me.keyskull.user

import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.Json

import scala.collection.immutable.Seq

/**
  * Created by keyskull on 2017/2/17.
  */

sealed trait AuthCommand
object AuthCommand extends JsonSerializerRegistry{
  override def serializers: Seq[JsonSerializer[_]] = Vector(
    JsonSerializer(Json.format[TokenAuth]),
    JsonSerializer(Json.format[AuthDone])
  )
}

case class TokenAuth(certificate:String) extends AuthCommand with ReplyType[AuthDone]
final case class AuthDone(entityId:String,token:String)



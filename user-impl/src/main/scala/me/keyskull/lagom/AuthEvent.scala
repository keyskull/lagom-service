package me.keyskull.lagom

import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventShards, AggregateEventTag}

/**
  * Created by keyskull on 2017/2/17.
  */

object AuthEvent{
  val NumShards = 20
  // second param is optional, defaults to the class name
  val Tag = AggregateEventTag.sharded[AuthEvent](NumShards)

}

sealed trait AuthEvent extends AggregateEvent[AuthEvent]{
  override def aggregateTag: AggregateEventShards[AuthEvent] = AuthEvent.Tag
}

case class CheckCertificate(entityId:String,certificate:String) extends AuthEvent

package me.keyskull.lagom

/**
  * Created by keyskull on 2017/2/17.
  */
sealed trait AuthState

object AuthState{
case object Normal extends AuthState
  case class Finish(certificate: String) extends AuthState
}
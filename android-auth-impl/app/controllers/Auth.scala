package controllers

import java.io.File

import controllers.AssetInfo.resource
import controllers.Assets.digest
import play.api.mvc.{Action, Controller}
import play.twirl.api.Html

import scala.concurrent.blocking

/**
  * Created by keyskull on 2017/1/26.
  */
class Auth extends Controller{
  def index() =Action {
    Ok(views.html.index())
  }

  def getPath:String = new File(".").getPath + "public"
}

object Auth{
//  val application = new DefaultApplication(new File("."), this.getClass.getClassloader, None, Play.Mode.Dev)
}
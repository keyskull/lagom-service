//import com.lightbend.lagom.scaladsl.api.ServiceCall
//
//import scala.concurrent.Future
//
///**
//  * Created by keyskull on 2017/1/24.
//  */
//class AuthService_impl extends me.keyskull.lagom.AuthService {
//  override def checkUser: ServiceCall[String, String] = ServiceCall { userName =>
//    Future.successful {
////      val token = FirebaseAuth.getInstance().createCustomToken(userName)
////      FirebaseAuth.getInstance().verifyIdToken(token).addOnSuccessListener()
////      token
//      """
//        |<!DOCTYPE html>
//        |<html>
//        |<head>
//        |<script src="https://www.gstatic.com/firebasejs/live/3.6/firebase.js"></script>
//        |</head>
//        |</html>
//      """.stripMargin
//    }
//  }
//}

package me.keyskull.lagom

import com.google.inject.Inject
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry
import me.keyskull.user.{AuthDone, AuthService, TokenAuth, UserService}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by keyskull on 2017/1/24.
  */

class UserServiceImpl(
                        persistentEntities: PersistentEntityRegistry)(implicit ec: ExecutionContext) extends UserService {

  override def checkUser: ServiceCall[String, String] = ServiceCall { userId =>
    import scala.concurrent.duration._

//      val token = FirebaseAuth.getInstance().createCustomToken(userId)
//      FirebaseAuth.getInstance().verifyIdToken(token).addOnSuccessListener()
//      token

    persistentEntities.refFor[UserEntity](userId)
        .ask(TokenAuth("")).map(f=>f.token)

  }
}

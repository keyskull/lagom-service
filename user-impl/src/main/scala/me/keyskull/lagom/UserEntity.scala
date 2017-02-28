package me.keyskull.lagom

/**
  * Created by keyskull on 2017/2/16.
  */
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity
import me.keyskull.user.{AuthCommand, TokenAuth, AuthDone}






final class UserEntity extends PersistentEntity {

  override type Command = AuthCommand
  override type Event = AuthEvent
  override type State = AuthState

  override def initialState: AuthState = AuthState.Normal


  override def behavior: Behavior = Actions()
    .onCommand[TokenAuth, AuthDone] {
    case (TokenAuth(certificate), ctx, state) =>
      ctx.thenPersist(CheckCertificate(entityId,certificate)) { evt =>
//        com.google.firebase.auth.FirebaseAuth.getInstance().createCustomToken("")

        // After persist is done additional side effects can be performed
        ctx.reply(AuthDone(entityId,""))
      }
  }.onEvent {
    case (CheckCertificate(entityId, certificate), state) =>
      AuthState.Finish(certificate)
  }

}
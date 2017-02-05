///**
//  * Created by keyskull on 2017/1/26.
//  */
//import com.lightbend.lagom.scaladsl.api.ServiceLocator
//
//
//class AuthServiceApplicationLoader extends LagomApplicationLoader {
//
//  override def loadDevMode(context: LagomApplicationContext) =
//  new AuthServiceApplication(context) with LagomDevModeComponents
//
//  override def load(context: LagomApplicationContext) =
//  new AuthServiceApplication(context) {
//    override def serviceLocator = ServiceLocator.NoServiceLocator
//  }
//}

///**
//  * Created by keyskull on 2017/1/24.
//  */
//import com.softwaremill.macwire._
//
//abstract class AuthServiceApplication(context: LagomApplicationContext)
//  extends LagomApplication(context)
//    with AhcWSComponents {
//
//  override lazy val lagomServer = LagomServer.forServices(
//    bindService[me.keyskull.lagom.AuthService].to(wire[AuthService_impl])
//  )
//}
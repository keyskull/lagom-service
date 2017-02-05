package me.keyskull.lagom

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.{FirebaseApp, FirebaseOptions}
import play.api.Logger

/**
  * Created by keyskull on 2017/1/24.
  */
object Firebase {
    val options = new FirebaseOptions.Builder()
      .setServiceAccount(getClass.getResource("/wallet-fa7513176888.json").openStream())
      .setDatabaseUrl("https://wallet-9bdef.firebaseio.com/")
      .build()
    FirebaseApp.initializeApp(options)
    Logger.debug("Firebase Init")
}

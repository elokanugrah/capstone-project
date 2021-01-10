package elok.dicoding.made.capstoneproject

import android.app.Application
import elok.dicoding.made.capstoneproject.di.AppComponent
import elok.dicoding.made.capstoneproject.di.DaggerAppComponent
import elok.dicoding.made.core.di.CoreComponent
import elok.dicoding.made.core.di.DaggerCoreComponent

open class MyApplication : Application() {

  private val coreComponent: CoreComponent by lazy {
    DaggerCoreComponent.factory().create(applicationContext)
  }

  val appComponent: AppComponent by lazy {
    DaggerAppComponent.factory().create(coreComponent)
  }
}
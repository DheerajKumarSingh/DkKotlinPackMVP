package dk.kotlinpack.mvp.di.component
import dagger.Component
import dk.kotlinpack.mvp.di.module.ActivityModule
import dk.kotlinpack.mvp.di.module.ApplicationModule
import dk.kotlinpack.mvp.di.module.HttpModule
import dk.kotlinpack.mvp.di.module.RxThreadModule
import dk.kotlinpack.mvp.service.ServiceUserFCMToken
import dk.kotlinpack.mvp.ui.base.BaseFragment
import dk.kotlinpack.mvp.ui.login.LoginActivity
import dk.kotlinpack.mvp.ui.profile.PartnerProfileFragment
import dk.kotlinpack.mvp.ui.profile.UserProfileFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ActivityModule::class,ApplicationModule::class,
    HttpModule::class,RxThreadModule::class))
      interface AppComponent {
   // fun inject(fragment: MainFragment)
    fun inject(activity: LoginActivity)
    fun inject(service: ServiceUserFCMToken)
    fun inject(baseFragment: BaseFragment)
    fun inject(fragmentProfile: UserProfileFragment)
  //  fun inject(fragment: RepoFragment)
}
package dk.kotlinpack.mvp
import android.app.Application
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerApplication
import dk.kotlinpack.mvp.di.component.AppComponent
import dk.kotlinpack.mvp.di.component.DaggerAppComponent
import dk.kotlinpack.mvp.di.module.ApplicationModule
import dk.kotlinpack.mvp.di.module.HttpModule

@Suppress("DEPRECATION")
class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent()
    }

    protected  fun createComponent() {
        component = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .httpModule(HttpModule())
            .build()
    }
}
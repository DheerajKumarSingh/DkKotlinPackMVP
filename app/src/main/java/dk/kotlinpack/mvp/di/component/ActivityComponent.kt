package dk.kotlinpack.mvp.di.component
import dagger.Component
import dk.kotlinpack.mvp.di.module.ActivityModule
import dk.kotlinpack.mvp.di.scope.PerActivity

@PerActivity
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
}
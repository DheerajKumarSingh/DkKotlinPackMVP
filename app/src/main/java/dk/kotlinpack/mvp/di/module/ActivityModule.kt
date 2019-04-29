package dk.kotlinpack.mvp.di.module
import android.app.Activity
import dagger.Module
import dagger.Provides
import dk.kotlinpack.mvp.di.qualifier.ActivityContext
import dk.kotlinpack.mvp.di.scope.PerActivity

@Module
class ActivityModule(val activity:Activity) {

    @PerActivity
    @ActivityContext
    @Provides
    fun provideContext():Activity=activity
}
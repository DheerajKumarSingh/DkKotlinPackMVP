package dk.kotlinpack.mvp.di.module
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import dk.kotlinpack.mvp.di.qualifier.ApplicaitonContext
import javax.inject.Singleton

@Module
class ApplicationModule(val application:Application) {

    @Provides
    @Singleton
    @ApplicaitonContext
    fun provideContext(): Application = this.application

    @Provides
    @Singleton
    fun provideInputMethod() = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

}


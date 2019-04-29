package dk.kotlinpack.mvp.di.module
import dagger.Module
import dagger.Provides
import dk.kotlinpack.BuildConfig
import dk.kotlinpack.mvp.api.NetWorkAPI
import dk.kotlinpack.mvp.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HttpModule {
    @Provides
    @Singleton

    fun provideHttpLogging(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
     fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val build: Retrofit = Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return build
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NetWorkAPI = retrofit.create(NetWorkAPI::class.java)
}
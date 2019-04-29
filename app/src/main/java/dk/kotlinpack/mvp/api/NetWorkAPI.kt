package dk.kotlinpack.mvp.api
import dk.kotlinpack.mvp.model.User
import dk.kotlinpack.mvp.model.Repo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface  NetWorkAPI {

    @GET("users/{user}")
    fun getUser(@Path("user") user: String): Observable<User>

    @GET("users/{user}/repos")
    fun getRepo(@Path("user") user: String): Observable<List<Repo>>

}
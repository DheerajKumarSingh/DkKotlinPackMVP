package dk.kotlinpack.mvp.model
import com.google.gson.annotations.SerializedName

class User constructor(@SerializedName("login") val username: String = "",
                       @SerializedName("id") val id: Int = 0,
                       @SerializedName("avatar_url") val imageUrl: String = "",
                       @SerializedName("repos_url") val repoUrl: String = "")

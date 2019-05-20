package guillaume.agis.babylonhealth.api

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.PostDao
import guillaume.agis.babylonhealth.model.User
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

/**
 * Resources associated to the Post HTTP requests.
 */
interface PostResource {

    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Single<User>

    @GET("posts")
    fun getPosts(): Single<List<PostDao>>

    // see comment in the PostsRepositoryImpl, it proposes before performance/  use less memory :)
    @Streaming
    @GET("posts")
    fun getPostsWithoutParsing(): Single<ResponseBody>

    @GET("comments")
    fun getComments(@Query("postId") postId: Int): Single<List<Comment>>

}
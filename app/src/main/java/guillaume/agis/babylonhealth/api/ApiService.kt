package guillaume.agis.babylonhealth.api

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.PostDao
import guillaume.agis.babylonhealth.model.User
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

/**
 * Service associated to the HTTP request.
 * Improvement : Could be split up by resources in the future (UserService, PostService, CommentService...)
 * to avoid having all the resources in one (messy) file.
 * I decided to keep it in 1 file  for this test, but I would obviously split it up for a bigger app :)
 */

interface ApiService {

    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Single<User>

    @GET("posts")
    fun getPosts(): Single<List<PostDao>>

    // see comment in the PostsRepositoryImpl, it proposes before performance/  use less memory :)
    @Streaming
    @GET("posts")
    fun getPostsWithoutParsing(): Single<ResponseBody>

    @GET("comments")
    fun getComments(): Single<List<Comment>>

}
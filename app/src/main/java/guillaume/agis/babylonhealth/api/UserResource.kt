package guillaume.agis.babylonhealth.api

import guillaume.agis.babylonhealth.model.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Resources associated to the User HTTP request.
 */
interface UserResource {

    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Single<User>

}
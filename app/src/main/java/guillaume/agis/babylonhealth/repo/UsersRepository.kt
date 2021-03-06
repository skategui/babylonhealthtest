package guillaume.agis.babylonhealth.repo

import guillaume.agis.babylonhealth.model.User
import io.reactivex.Single

interface UsersRepository {

    /**
     * Get the user from a user ID
     *  @param userId id of the user to load
     *  @return [Single] [User] user found
     */
    fun getUserById(userId: Int): Single<User>
}
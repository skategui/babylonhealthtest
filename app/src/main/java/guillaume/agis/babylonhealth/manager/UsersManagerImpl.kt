package guillaume.agis.babylonhealth.manager

import guillaume.agis.babylonhealth.model.User
import guillaume.agis.babylonhealth.repo.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * User Manager is responsible to manage all the business logic related to the User
 * such as taking the decision to fetch the data from the server or locally (in memory for this test).
 */
class UsersManagerImpl
@Inject constructor(private val usersRepository: UsersRepository) : UsersManager {
    // store locally the users already fetched from the server to avoid loading_animation them again
    // could be store in a datastore, such as Room if we wish to have some consistency, not impl here
    private val users: ArrayList<User> = arrayListOf()

    /**
     * Get the user from a user ID, from the server if not already fetched, or locally
     *  @param userId id of the user to load
     *  @return [Single] [User] user found
     */
    override fun getUserById(userId: Int): Single<User> {
        val userFound = exist(userId)
        return userFound?.let { Single.just(it) }
            ?: usersRepository.getUserById(userId = userId)
                .doOnSuccess { user -> addUser(user) }
    }

    /**
     * Verify if the user has already been fetched for the server or not
     *  @param userId id of the user to check if already loaded
     *  @return [Single] [User] user found, if any, null otherwise
     */
    private fun exist(userId: Int) = users.find { it.id == userId }


    /**
     * Add a user in the list of users already loaded, if not already in the list
     *  @param user  user to add
     */
    private fun addUser(user: User) {
        if (!users.contains(user))
            users.add(user)
    }

}


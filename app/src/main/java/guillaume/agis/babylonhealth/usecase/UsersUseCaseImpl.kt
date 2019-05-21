package guillaume.agis.babylonhealth.usecase

import guillaume.agis.babylonhealth.datastore.UsersDatastore
import guillaume.agis.babylonhealth.model.User
import guillaume.agis.babylonhealth.repo.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * User UseCae is responsible to manage all the business logic related to the User
 */
class UsersUseCaseImpl
@Inject constructor(private val usersRepository: UsersRepository,
                    private val usersDatastore: UsersDatastore) : UsersUseCase {

    /**
     * Get the user from a user ID, from the server if not already fetched, or locally from the datastore
     *  @param userId id of the user to load
     *  @return [Single] [User] user found, or an error if user not found
     */
    override fun getUserById(userId: Int): Single<User> {
        val userFound = usersDatastore.get(userId)
        return userFound?.let { Single.just(it) }
            ?: usersRepository.getUserById(userId = userId)
                .doOnSuccess { user -> usersDatastore.addUser(user) }
    }

}


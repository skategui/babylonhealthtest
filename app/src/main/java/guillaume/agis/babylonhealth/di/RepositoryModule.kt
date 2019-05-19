package guillaume.agis.babylonhealth.di


import dagger.Binds
import dagger.Module
import guillaume.agis.babylonhealth.repo.PostsRepository
import guillaume.agis.babylonhealth.repo.PostsRepositoryImpl
import guillaume.agis.babylonhealth.repo.UsersRepository
import guillaume.agis.babylonhealth.repo.UsersRepositoryImpl


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsUserRepository(repositoryImpl: UsersRepositoryImpl): UsersRepository

    @Binds
    abstract fun binddPostRepository(repositoryImpl: PostsRepositoryImpl): PostsRepository

}
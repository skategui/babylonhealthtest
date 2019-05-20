package guillaume.agis.babylonhealth.di

import dagger.Binds
import dagger.Module
import guillaume.agis.babylonhealth.usecase.PostsUseCase
import guillaume.agis.babylonhealth.usecase.PostsUseCaseImpl
import guillaume.agis.babylonhealth.usecase.UsersUseCase
import guillaume.agis.babylonhealth.usecase.UsersUseCaseImpl
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindPostsUseCase(postsUserCase: PostsUseCaseImpl): PostsUseCase

    @Binds
    abstract fun bindUserUseCase(userUserCase: UsersUseCaseImpl): UsersUseCase

}
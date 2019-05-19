package guillaume.agis.babylonhealth.ui.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.repo.PostsRepository
import guillaume.agis.babylonhealth.repo.UsersRepository


@Module
class EspressoRepositoryModule {

    @Provides
    fun provideMockPostsRepository() = mock<PostsRepository>()

    @Provides
    fun provideMockUserRepository() = mock<UsersRepository>()
}

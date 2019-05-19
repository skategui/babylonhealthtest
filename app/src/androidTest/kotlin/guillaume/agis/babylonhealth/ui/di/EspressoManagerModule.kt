package guillaume.agis.babylonhealth.ui.di

import com.nhaarman.mockitokotlin2.whenever
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.manager.UsersManager
import guillaume.agis.babylonhealth.utils.DataBuilder
import io.reactivex.Single
import org.mockito.Mockito.*


@Module
class EspressoManagerModule {

    @Provides
    fun provideMockPostsManager(): PostsManager {

        val postsManager = mock(PostsManager::class.java)
        whenever(postsManager.getCommentsByPostId(com.nhaarman.mockitokotlin2.any())).thenReturn(Single.just(DataBuilder.provideCommentsList()))
        whenever(postsManager.getPosts()).thenReturn(Single.just(DataBuilder.providePostsList()))
        return postsManager
    }

    @Provides
    fun provideMockUserManager(): UsersManager {

        val usersManager = mock(UsersManager::class.java)
        whenever(usersManager.getUserById(com.nhaarman.mockitokotlin2.any())).thenReturn(Single.just(DataBuilder.buildUser()))
        return usersManager
    }

}
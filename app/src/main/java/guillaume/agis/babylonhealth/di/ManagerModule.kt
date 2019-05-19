package guillaume.agis.babylonhealth.di

import dagger.Binds
import dagger.Module
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.manager.PostsManagerImpl
import guillaume.agis.babylonhealth.manager.UsersManager
import guillaume.agis.babylonhealth.manager.UsersManagerImpl
import javax.inject.Singleton

@Module
abstract class ManagerModule {

    @Singleton
    @Binds
    abstract fun bindPostsManager(postsManager: PostsManagerImpl): PostsManager

    @Singleton
    @Binds
    abstract fun bindUserManager(userManager: UsersManagerImpl): UsersManager

}
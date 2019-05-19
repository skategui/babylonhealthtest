package guillaume.agis.babylonhealth.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import guillaume.agis.babylonhealth.ui.detail.PostDetailActivity
import guillaume.agis.babylonhealth.ui.detail.PostDetailModule
import guillaume.agis.babylonhealth.ui.list.ListPostsActivity
import guillaume.agis.babylonhealth.ui.list.PostsListModule

@Module
abstract class BabylonActivityModule {

    @ContributesAndroidInjector(modules = [PostsListModule::class])
    abstract fun contributeListsPostsActivityInjector(): ListPostsActivity

    @ContributesAndroidInjector(modules = [PostDetailModule::class])
    abstract fun contributePostDetailActivityInjector(): PostDetailActivity
}
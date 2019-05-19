package guillaume.agis.babylonhealth.ui.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import guillaume.agis.babylonhealth.ui.detail.EspressoPostDetailModule
import guillaume.agis.babylonhealth.ui.detail.PostDetailActivity
import guillaume.agis.babylonhealth.ui.list.EspressoPostsListModule
import guillaume.agis.babylonhealth.ui.list.ListPostsActivity

@Module
abstract class EspressoBabylonActivityModule {

    @ContributesAndroidInjector(modules = [EspressoPostsListModule::class])
    abstract fun contributeListsPostsActivityInjector(): ListPostsActivity

    @ContributesAndroidInjector(modules = [EspressoPostDetailModule::class])
    abstract fun contributePostDetailActivityInjector(): PostDetailActivity
}
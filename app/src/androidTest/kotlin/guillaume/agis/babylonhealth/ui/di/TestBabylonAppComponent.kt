package guillaume.agis.babylonhealth.ui.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import guillaume.agis.babylonhealth.di.BabylonActivityModule
import guillaume.agis.babylonhealth.di.BabylonAppComponent
import guillaume.agis.babylonhealth.di.BabylonCustomViewModule
import guillaume.agis.babylonhealth.di.HttpClientModule
import guillaume.agis.babylonhealth.ui.detail.PostDetailActivityTest
import guillaume.agis.babylonhealth.ui.list.ListPostsActivityTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        HttpClientModule::class,
        EspressoManagerModule::class,
        EspressoRepositoryModule::class,
        EspressoBabylonActivityModule::class,
        BabylonCustomViewModule::class
    ]
)
interface TestBabylonAppComponent : BabylonAppComponent {
    fun inject(mainActivityTest: ListPostsActivityTest)
    fun inject(postDetailActivityTest: PostDetailActivityTest)
}
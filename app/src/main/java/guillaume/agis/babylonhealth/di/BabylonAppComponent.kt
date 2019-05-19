package guillaume.agis.babylonhealth.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import guillaume.agis.babylonhealth.BabylonApplication
import guillaume.agis.babylonhealth.ui.customview.CommentsListView
import javax.inject.Singleton

/**
 * dependencies needed in the app
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ManagerModule::class,
        HttpClientModule::class,
        RepositoryModule::class,
        BabylonActivityModule::class,
        BabylonCustomViewModule::class
    ]
)
interface BabylonAppComponent : AndroidInjector<BabylonApplication> {
    fun inject(commentsListView: CommentsListView)
}
package guillaume.agis.babylonhealth.di

import android.content.Context
import dagger.BindsInstance
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
        UseCaseModule::class,
        UserDatastoreModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        BabylonActivityModule::class,
        BabylonCustomViewModule::class
    ]
)
interface BabylonAppComponent : AndroidInjector<BabylonApplication> {
    fun inject(commentsListView: CommentsListView)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): BabylonAppComponent
    }
}
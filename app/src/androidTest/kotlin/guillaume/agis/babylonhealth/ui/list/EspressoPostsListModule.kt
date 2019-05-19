package guillaume.agis.babylonhealth.ui.list

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.di.DiffCallbackModule
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.ui.utils.DiffCallback

@Module(includes = [DiffCallbackModule::class])
class EspressoPostsListModule {

    @Provides
    fun providesListPostsViewModelFactory(
        postManager: PostsManager, httpErrorUtils: HttpErrorUtils
    ): ViewModelProvider.Factory {
        return ListPostsViewModel.Factory(postManager, httpErrorUtils)
    }


    @Provides
    fun providesListPostsAdapterImpl(diffCallback: DiffCallback): ListPostsAdapter {
        return ListPostsAdapterImpl(diffCallback)
    }
}
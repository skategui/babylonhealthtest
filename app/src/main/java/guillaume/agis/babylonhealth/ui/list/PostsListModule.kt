package guillaume.agis.babylonhealth.ui.list

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.di.DiffCallbackModule
import guillaume.agis.babylonhealth.usecase.PostsUseCase
import guillaume.agis.babylonhealth.ui.utils.DiffCallback

@Module(includes = [DiffCallbackModule::class])
class PostsListModule {

    @Provides
    fun providesListPostsViewModelFactory(
        postUseCase: PostsUseCase, httpErrorUtils: HttpErrorUtils
    ): ViewModelProvider.Factory {
        return ListPostsViewModel.Factory(postUseCase, httpErrorUtils)
    }


    @Provides
    fun providesListPostsAdapterImpl(diffCallback: DiffCallback): ListPostsAdapter {
        return ListPostsAdapterImpl(diffCallback)
    }


}
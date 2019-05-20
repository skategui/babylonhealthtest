package guillaume.agis.babylonhealth.ui.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.di.DiffCallbackModule
import guillaume.agis.babylonhealth.usecase.PostsUseCase

@Module(includes = [DiffCallbackModule::class])
class PostDetailModule {

    @Provides
    fun providesPostDetailViewModelFactory(
        postUseCase: PostsUseCase,
        httpErrorUtils: HttpErrorUtils
    ): ViewModelProvider.Factory {
        return PostDetailViewModel.Factory(postUseCase, httpErrorUtils)
    }
}
package guillaume.agis.babylonhealth.ui.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.di.DiffCallbackModule
import guillaume.agis.babylonhealth.manager.PostsManager

@Module(includes = [DiffCallbackModule::class])
class EspressoPostDetailModule {

    @Provides
    fun providesPostDetailViewModelFactory(
        postManager: PostsManager,
        httpErrorUtils: HttpErrorUtils
    ): ViewModelProvider.Factory {
        return PostDetailViewModel.Factory(postManager, httpErrorUtils)
    }
}
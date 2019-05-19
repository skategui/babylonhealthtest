package guillaume.agis.babylonhealth.di

import dagger.Binds
import dagger.Module
import guillaume.agis.babylonhealth.ui.detail.CommentsAdapter
import guillaume.agis.babylonhealth.ui.detail.CommentsAdapterImpl

@Module(includes = [DiffCallbackModule::class])
abstract class BabylonCustomViewModule {

    @Binds
    abstract fun bindCommentsAdapter(commentsAdapter: CommentsAdapterImpl): CommentsAdapter

}
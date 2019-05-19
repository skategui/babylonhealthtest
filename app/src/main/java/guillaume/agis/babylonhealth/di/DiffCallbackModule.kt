package guillaume.agis.babylonhealth.di

import dagger.Module
import dagger.Provides
import guillaume.agis.babylonhealth.ui.utils.DiffCallback

@Module
class DiffCallbackModule {

    @Provides
    fun providesDiffCallbackModule() = DiffCallback()

}
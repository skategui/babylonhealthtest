package guillaume.agis.babylonhealth

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import guillaume.agis.babylonhealth.di.BabylonAppComponent
import guillaume.agis.babylonhealth.di.DaggerBabylonAppComponent
import javax.inject.Inject

// App , init dagger and the AndroidInjector
class BabylonApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerBabylonAppComponent.builder()
            .context(this)
            .build()
            .also { babylonAppComponent = it }
        babylonAppComponent.inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    companion object {
        @JvmStatic
        lateinit var babylonAppComponent: BabylonAppComponent
    }
}
package guillaume.agis.babylonhealth

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import guillaume.agis.babylonhealth.di.BabylonAppComponent
import guillaume.agis.babylonhealth.di.DaggerBabylonAppComponent
import javax.inject.Inject

open class BabylonApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        initDagger()

    }

    open fun initDagger() {
        babylonAppComponent = DaggerBabylonAppComponent.builder()
            .build()
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
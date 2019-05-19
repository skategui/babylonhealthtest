package guillaume.agis.babylonhealth.ui.di

import guillaume.agis.babylonhealth.BabylonApplication

class TestBabylonApp : BabylonApplication() {


    override fun initDagger() {
        Companion.babylonAppComponent = DaggerTestBabylonAppComponent
            .builder()
            .build()
            .also { babylonAppComponent = it }
    }


}
package guillaume.agis.babylonhealth.ui.di

import android.app.Instrumentation
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import guillaume.agis.babylonhealth.BabylonApplication

class EspressoTestRunner: AndroidJUnitRunner() {

    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?)
            = Instrumentation.newApplication(BabylonApplication::class.java, context)

}
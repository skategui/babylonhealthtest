package guillaume.agis.babylonhealth.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity : AppCompatActivity() {
    // to avoid memory leak
    protected var disposables = CompositeDisposable()
    // BT Utils from the toothbrush module, it's not required. It only contains helpers

    public override fun onDestroy() {
        super.onDestroy()
        // free the memory
        disposables.dispose()
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }


}

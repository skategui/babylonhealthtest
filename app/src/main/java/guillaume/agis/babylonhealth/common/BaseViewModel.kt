package guillaume.agis.babylonhealth.common

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import guillaume.agis.babylonhealth.model.Post
import io.reactivex.disposables.CompositeDisposable

/**
 * Base ViewModel to manage the disposables
 */
abstract class BaseViewModel<T>(var state: T) : ViewModel(), DefaultLifecycleObserver {
    protected val disposables = CompositeDisposable()
    private val viewStateRelay = BehaviorRelay.create<T>()
    open val viewStateObservable = viewStateRelay
        .startWith(state)
        .hide()

    override fun onStart(owner: LifecycleOwner) {
        emitViewState(state)
    }

    protected fun emitViewState(viewState: T) {
        this.state = viewState
        viewStateRelay.accept(viewState)
        this.state = resetActions()
    }

    abstract fun resetActions(): T


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}
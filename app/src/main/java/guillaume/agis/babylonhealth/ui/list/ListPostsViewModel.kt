package guillaume.agis.babylonhealth.ui.list

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.api.io
import guillaume.agis.babylonhealth.common.BaseViewModel
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.model.Post
import javax.inject.Inject


/**
 * Viewmodel responsible for the business logic associated to the list of posts view
 */
class ListPostsViewModel
@Inject constructor(
    private val postManager: PostsManager,
    private val httpErrorUtils: HttpErrorUtils
) : BaseViewModel<ListPostsViewState>(ListPostsViewState()),
    DefaultLifecycleObserver {

    // clean the action
    override fun resetActions() = state.copy(action = Actions.None)

    /**
     * Associated to the lifecycle of the activity; load the list of posts when the activity
     * is created
     *  @param owner LifecycleOwner
     */
    override fun onCreate(owner: LifecycleOwner) {
        loadPosts()
    }


    /**
     * Reload posts list
     */
    fun onReloadClicked() {
        loadPosts()
    }

    /**
     * Callback when a row of the list of posts is clicked; emit a OpenPostDetail
     * action with the post associated to the row clicked
     *  @param post post associated to the row clicked
     */
    fun onRowClicked(post: Post) {
        emitViewState(state.copy(action = Actions.OpenPostDetail(post), isLoading = false))
    }
    /**
     * Load the list of posts to display
     */
    private fun loadPosts() {
        disposables.add(
            postManager.getPosts()
                .doOnSubscribe { emitViewState(state.copy(isLoading = true)) }
                .io()
                .subscribe(this::success, this::postsErrors)
        )
    }

    /**
     * Emit the list of posts if not empty, otherwise emit a message to the user to inform that the list is empty
     *  @param posts Posts loaded
     */
    private fun success(posts: List<Post>) {
        if (posts.isEmpty())
            emitViewState(state.copy(action = Actions.DisplayEmptyListMessage, isLoading = false))
        else
            emitViewState(state.copy(posts = posts, isLoading = false))
    }

    /**
     * Error thrown from the request to lost the post. Emit the error with the message to the view
     */
    private fun postsErrors(throwable: Throwable) {
        when (httpErrorUtils.hasLostInternet(throwable)) {
            true -> emitViewState(state.copy(action = Actions.NoInternet))
            false -> emitViewState(state.copy(action = Actions.Error(throwable.message), isLoading = false))
        }
    }


    class Factory
    @Inject constructor(
        private val postManager: PostsManager,
        private val httpErrorUtils: HttpErrorUtils
    ) : ViewModelProvider.Factory {
        @SuppressWarnings("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ListPostsViewModel(postManager, httpErrorUtils) as T
        }
    }
}
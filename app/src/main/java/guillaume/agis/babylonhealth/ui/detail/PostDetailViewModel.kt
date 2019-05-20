package guillaume.agis.babylonhealth.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.api.io
import guillaume.agis.babylonhealth.common.BaseViewModel
import guillaume.agis.babylonhealth.usecase.PostsUseCase
import guillaume.agis.babylonhealth.model.Post
import javax.inject.Inject

/**
 * Viewmodel responsible for the business logic associated to the post detail view
 */
class PostDetailViewModel
@Inject constructor(
    private val postUseCase: PostsUseCase,
    private val httpErrorUtils: HttpErrorUtils
) : BaseViewModel<PostDetailViewState>(PostDetailViewState()) {

    private lateinit var post: Post

    // clean the action
    override fun resetActions() = state.copy(action = Actions.None)

    /**
     * set the post to load the comments associated to it
     *  @param post post associated
     */
    fun setPost(post: Post) {
        this.post = post
        emitViewState(state.copy(post = post))
        loadComments(post.id)
    }


    /**
     * Emit a the SeeComments action when the comment button is clicked
     */
    fun onCommentBtnClicked() {
        emitViewState(state.copy(action = Actions.SeeComments))
    }

    /**
     * Emit a the OpenEmail action when the email button is clicked
     */
    fun onEmailClicked() {
        emitViewState(
            state.copy(action = Actions.OpenEmail(email = post.user.email))
        )
    }

    /**
     * Load comments list given a post id
     *  @param postId id of the post associated
     */
    private fun loadComments(postId: Int) {
        disposables.add(
            postUseCase.getCommentsByPostId(postId)
                .io()
                .subscribe({ comments ->
                    emitViewState(state.copy(comments = comments))
                }, this::postsErrors)
        )
    }

    /**
     * Emit the error to the view
     *  @param throwable throwable thrown
     */
    private fun postsErrors(throwable: Throwable) {

        when (httpErrorUtils.hasLostInternet(throwable)) {
            true -> emitViewState(state.copy(action = Actions.NoInternet))
            false -> emitViewState(state.copy(action = Actions.Error(throwable.message)))
        }
    }


    class Factory
    @Inject constructor(
        private val postUseCase: PostsUseCase,
        private val httpErrorUtils: HttpErrorUtils
    ) : ViewModelProvider.Factory {
        @SuppressWarnings("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PostDetailViewModel(postUseCase, httpErrorUtils) as T
        }
    }
}
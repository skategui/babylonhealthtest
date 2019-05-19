package guillaume.agis.babylonhealth.ui.detail

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.rule.BaseRule
import guillaume.agis.babylonhealth.utils.DataBuilder
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

/**
 * Used to test async code in RxAndroid in the test
 */
class PostDetailViewModelTest : BaseRule() {

    private lateinit var viewModel: PostDetailViewModel
    private val postManager = mock<PostsManager>()
    private val comments = listOf<Comment>(mock(), mock())

    private val post = DataBuilder.providePost()


    @Before
    fun setUp() {
        viewModel = PostDetailViewModel(postManager, HttpErrorUtils())
        whenever(postManager.getCommentsByPostId(any())).thenReturn(Single.just(comments))
    }

    @Test
    fun `Given the initialisation of the viewModel then emit an empty PostDetailViewState `() {
        val testObserver = viewModel.viewStateObservable.test()
        testObserver
            .assertNoErrors()
            .assertValue(PostDetailViewState())
    }

    @Test
    fun `Given the set of a post of the viewModel then emit this post`() {
        val testObserver = viewModel.viewStateObservable.test()
        viewModel.setPost(post)
        testObserver
            .assertNoErrors()
            .assertValueAt(1, PostDetailViewState(post = post))
    }

    @Test
    fun `Given a list of comments when viewModel is created then display this list`() {

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setPost(post)
        testObserver
            .assertNoErrors()
            .assertValueAt(2, PostDetailViewState(post = post, comments = comments))
    }


    @Test
    fun `Given an error when loading the comments then send an error`() {
        val errorMsg = "errorMsg"
        whenever(postManager.getCommentsByPostId(any())).thenReturn(Single.error(Throwable(errorMsg)))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setPost(post)

        val state = testObserver
            .assertNoErrors()
            .values()[2]

        assertThat(state.post).isEqualTo(post)
        assertThat(state.action.cast<Actions.Error>().error).isEqualTo(errorMsg)
    }

    @Test
    fun `Given no internet connexion when loading the comments then emit No internet action`() {
        whenever(postManager.getCommentsByPostId(any())).thenReturn(Single.error(UnknownHostException()))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setPost(post)

        testObserver
            .assertNoErrors()
            .assertValueAt(2, viewModel.state.copy(action = Actions.NoInternet))
    }

    @Test
    fun `Given the button visible When the comments button is clicked then emit SeeComments`() {
        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCommentBtnClicked()

        testObserver
            .assertNoErrors()
            .assertValueAt(1, viewModel.state.copy(action = Actions.SeeComments))
    }

    @Test
    fun `Given a post set When the email icon is clicked then emit OpenEmail with the user email`() {
        val testObserver = viewModel.viewStateObservable.test()

        viewModel.setPost(post)
        viewModel.onEmailClicked()

        val state = testObserver
            .assertNoErrors()
            .values()[3]

        assertThat(state.post).isEqualTo(post)
        assertThat(state.action.cast<Actions.OpenEmail>().email).isEqualTo(post.user.email)
    }

    @Test
    fun `Given a viewmodel when calling resetAction then default the action of the viewState`() {

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.resetActions()

        testObserver
            .assertNoErrors()
            .assertValue(PostDetailViewState(action = Actions.None))
    }
}

internal fun <T> Actions.cast(): T = this as T
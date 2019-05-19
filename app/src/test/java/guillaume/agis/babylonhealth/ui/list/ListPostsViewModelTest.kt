package guillaume.agis.babylonhealth.ui.list


import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import guillaume.agis.babylonhealth.api.HttpErrorUtils
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.rule.BaseRule
import guillaume.agis.babylonhealth.utils.DataBuilder
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ListPostsViewModelTest : BaseRule() {

    private lateinit var viewModel: ListPostsViewModel

    private val postManager = mock<PostsManager>()
    private val posts = DataBuilder.providePostsList()


    @Before
    fun setUp() {
        viewModel = ListPostsViewModel(postManager, HttpErrorUtils())
    }

    @Test
    fun `Given the initialisation of the viewModel then emit an empty ListPostsViewState `() {
        val testObserver = viewModel.viewStateObservable.test()
        testObserver
            .assertNoErrors()
            .assertValue(ListPostsViewState())
    }

    @Test
    fun `Given a list of posts when viewModel is created then display this list`() {

        whenever(postManager.getPosts()).thenReturn(Single.just(posts))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        testObserver
            .assertNoErrors()
            .assertValueAt(2, ListPostsViewState(isLoading = false, posts = posts))
    }


    @Test
    fun `Given the viewmodel created when the posts list is loading then display the loader `() {

        whenever(postManager.getPosts()).thenReturn(Single.just(posts))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())
        testObserver
            .assertNoErrors()
            .assertValueAt(1, ListPostsViewState(isLoading = true))
    }


    @Test
    fun `Given the viewmodel created when the refresh button is clicked then load the posts list`() {

        whenever(postManager.getPosts()).thenReturn(Single.just(posts))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onReloadClicked()
        testObserver
            .assertNoErrors()
            .assertValueAt(1, ListPostsViewState(isLoading = true))
    }

    @Test
    fun `Given an empty list of posts when viewModel is created then send DisplayEmptyListMessage`() {
        whenever(postManager.getPosts()).thenReturn(Single.just(emptyList()))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())

        val state = testObserver
            .assertNoErrors()
            .values()[2]

        assertThat(state.isLoading).isFalse()
        assertThat(state.action is Actions.DisplayEmptyListMessage).isTrue()
    }

    @Test
    fun `Given an error when loading the posts then send an error`() {
        val errorMsg = "errorMsg"
        whenever(postManager.getPosts()).thenReturn(Single.error(Throwable(errorMsg)))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())


        val state = testObserver
            .assertNoErrors()
            .values()[2]

        assertThat(state.isLoading).isFalse()
        assertThat(state.action.cast<Actions.Error>().error).isEqualTo(errorMsg)
    }

    @Test
    fun `Given no internet connexion when loading the comments then emit No internet action`() {
        whenever(postManager.getPosts()).thenReturn(Single.error(UnknownHostException()))

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.onCreate(mock())

        testObserver
            .assertNoErrors()
            .assertValueAt(2, viewModel.state.copy(action = Actions.NoInternet))
    }


    @Test
    fun `Given an actual list of posts when a row is clicked then emit a OpenPostDetail with the post`() {
        whenever(postManager.getPosts()).thenReturn(Single.just(posts))

        val testObserver = viewModel.viewStateObservable.test()
        val post = DataBuilder.providePost()

        viewModel.onRowClicked(post)

        val state = testObserver
            .assertNoErrors()
            .values()[1]

        assertThat(state.isLoading).isFalse()
        assertThat(state.action.cast<Actions.OpenPostDetail>().post).isEqualTo(post)
    }


    @Test
    fun `Given a viewmodel when calling resetAction then default the action of the viewState`() {

        val testObserver = viewModel.viewStateObservable.test()

        viewModel.resetActions()

        testObserver
            .assertNoErrors()
            .assertValue(ListPostsViewState(action = Actions.None))
    }
}

internal fun <T> Actions.cast(): T = this as T
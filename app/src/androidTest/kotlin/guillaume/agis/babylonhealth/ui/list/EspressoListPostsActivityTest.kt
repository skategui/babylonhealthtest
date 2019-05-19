package guillaume.agis.babylonhealth.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.whenever
import com.zhuinden.espressohelper.*
import guillaume.agis.babylonhealth.BabylonApplication
import guillaume.agis.babylonhealth.R
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.ui.detail.PostDetailActivity
import guillaume.agis.babylonhealth.ui.di.TestBabylonApp
import guillaume.agis.babylonhealth.ui.di.TestBabylonAppComponent
import guillaume.agis.babylonhealth.ui.utils.CustomAssertions.Companion.hasItemCount
import guillaume.agis.babylonhealth.ui.utils.RecyclerViewMatcher.Companion.withRecyclerView
import guillaume.agis.babylonhealth.utils.DataBuilder
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import java.net.UnknownHostException
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
class ListPostsActivityTest {

    @get:Rule
    @Suppress("unused")
    internal var mockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var postManager: PostsManager


    private lateinit var testBabylonAppComponent: TestBabylonAppComponent

    @get:Rule
    val activityTestRule = ActivityTestRule<ListPostsActivity>(ListPostsActivity::class.java, false, false)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestBabylonApp
        app.initDagger()
        testBabylonAppComponent = BabylonApplication.babylonAppComponent as TestBabylonAppComponent
        testBabylonAppComponent.inject(app)
        testBabylonAppComponent.inject(this)
    }


    @Test
    fun should_display_list_posts() {

        activityTestRule.launchActivity(null)

        R.id.llState.checkIsNotDisplayed()
        R.id.tvTitle.checkIsVisible()
        R.id.tvTitle.checkHasAnyText()
        R.id.tvBody.checkIsVisible()
        R.id.tvBody.checkIsVisible()
        R.id.cardView.checkIsClickable()
        R.id.recyclerView.checkIsVisible()
        onView(withId(R.id.recyclerView)).check(hasItemCount(1))

        val posts = DataBuilder.providePostsList()

        for (index in 0..posts.size)
        {
            val post = posts[index]
            onView(
                withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(index, R.id.tvTitle)
            )
                .check(matches(withText(post.title)))

            onView(
                withRecyclerView(R.id.recyclerView)
                    .atPositionOnView(index, R.id.tvBody)
            )
                .check(matches(withText(post.body)))
        }



        R.id.recyclerView.performActionOnRecyclerItemAtPosition<ListPostsAdapterImpl.PostViewHolder>(
            0, ViewActions.click()
        )
        checkCurrentActivityIs<PostDetailActivity>()

        // intended(allOf(hasComponent(PostDetailActivity::class.java.name), hasExtra(PostDetailActivity.POST_SELECTED, post)))
    }


    @Test
    fun should_display_no_internet_connexion_msg() {

        whenever(postManager.getPosts()).thenReturn(Single.error(UnknownHostException()))

        activityTestRule.launchActivity(null)
        R.id.llState.checkIsVisible()
        R.id.tvStateTitle.checkContainsText(getCurrentActivity().getString(R.string.error_no_internet_connexion))
        R.id.tvReload.checkIsClickable()
    }

    @Test
    fun should_display_message_no_post() {

        whenever(postManager.getPosts()).thenReturn(Single.just(emptyList()))

        activityTestRule.launchActivity(null)

        R.id.llState.checkIsVisible()
        R.id.tvStateTitle.checkContainsText(getCurrentActivity().getString(R.string.error_no_post_available))
        R.id.tvReload.checkIsNotDisplayed()
    }

    @Test
    fun should_display_error_msg() {

        whenever(postManager.getPosts()).thenReturn(Single.error(Throwable("error")))

        activityTestRule.launchActivity(null)

        R.id.llState.checkIsVisible()
        R.id.tvStateTitle.checkContainsText(getCurrentActivity().getString(R.string.error_try_again_later))
        R.id.tvReload.checkIsNotDisplayed()
    }

}

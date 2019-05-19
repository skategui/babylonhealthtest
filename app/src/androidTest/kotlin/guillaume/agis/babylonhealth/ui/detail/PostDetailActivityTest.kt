package guillaume.agis.babylonhealth.ui.detail

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.whenever
import com.zhuinden.espressohelper.*
import guillaume.agis.babylonhealth.BabylonApplication
import guillaume.agis.babylonhealth.R
import guillaume.agis.babylonhealth.manager.PostsManager
import guillaume.agis.babylonhealth.ui.di.TestBabylonApp
import guillaume.agis.babylonhealth.ui.di.TestBabylonAppComponent
import guillaume.agis.babylonhealth.ui.utils.CustomAssertions
import guillaume.agis.babylonhealth.ui.utils.RecyclerViewMatcher
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
class PostDetailActivityTest {
    @get:Rule
    @Suppress("unused")
    internal var mockitoRule = MockitoJUnit.rule()

    @Inject
    lateinit var postManager: PostsManager


    private lateinit var testBabylonAppComponent: TestBabylonAppComponent


    @get:Rule
    val activityTestRule = ActivityTestRule<PostDetailActivity>(PostDetailActivity::class.java, false, false)

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
    fun should_display_post_detail_with_comments_list_available() {

        val intent = Intent()
        val post = DataBuilder.providePost()
        intent.putExtra(PostDetailActivity.POST_SELECTED, post)
        activityTestRule.launchActivity(intent)

        R.id.tvEmail.checkIsVisible()
        R.id.tvFullName.checkIsVisible()
        R.id.tvFullName.checkHasText(post.user.username)
        R.id.tvTitle.checkIsVisible()
        R.id.tvBody.checkIsVisible()
        R.id.tvTitle.checkHasText(post.title)
        R.id.tvBody.checkHasText(post.body)
        R.id.llSeeComments.checkIsVisible()
        R.id.llSeeComments.checkIsClickable()
        R.id.commentsList.checkIsNotDisplayed()
        R.id.llSeeComments.performClick()
        R.id.popup_title.checkHasText(post.title)


        val comments = DataBuilder.provideCommentsList()

        for (index in 0 until comments.size)
        {
            val comment = comments[index]
            Espresso.onView(
                RecyclerViewMatcher.withRecyclerView(R.id.recyclerCommentsView)
                    .atPositionOnView(index, R.id.tvFullName)
            )
                .check(ViewAssertions.matches(ViewMatchers.withText(comment.name)))

            Espresso.onView(
                RecyclerViewMatcher.withRecyclerView(R.id.recyclerCommentsView)
                    .atPositionOnView(index, R.id.tvBody)
            )
                .check(ViewAssertions.matches(ViewMatchers.withText(comment.body)))
        }

        Espresso.onView(ViewMatchers.withId(R.id.recyclerCommentsView)).check(CustomAssertions.hasItemCount(comments.size))

    }


    @Test
    fun should_display_post_detail_with_comments_button_not_viible() {

        whenever(postManager.getPosts()).thenReturn(Single.error(UnknownHostException()))

        val intent = Intent()
        val post = DataBuilder.providePost()
        intent.putExtra(PostDetailActivity.POST_SELECTED, post)
        activityTestRule.launchActivity(intent)

        R.id.tvEmail.checkIsVisible()
        R.id.tvFullName.checkIsVisible()
        R.id.tvFullName.checkHasText(post.user.username)
        R.id.tvTitle.checkIsVisible()
        R.id.tvBody.checkIsVisible()
        R.id.tvTitle.checkHasText(post.title)
        R.id.tvBody.checkHasText(post.body)
        R.id.llSeeComments.checkIsNotDisplayed()
        R.id.commentsList.checkIsNotDisplayed()
    }
}
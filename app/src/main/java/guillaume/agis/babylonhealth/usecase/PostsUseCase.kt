package guillaume.agis.babylonhealth.usecase

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.Post
import io.reactivex.Single

interface PostsUseCase {
    /**
     *  Get the posts list from the server
     *  @return [Single] [List] [Post]  list of posts fetched
     */
    fun getPosts(): Single<List<Post>>

    /**
     *  Get the postId of comments given a post ID
     *  @param postId id of the post to load
     *  @return [Single] [List] [Comment]  list of comments associated to this post
     */
    fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}



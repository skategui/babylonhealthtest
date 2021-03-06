package guillaume.agis.babylonhealth.repo

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.PostDao
import io.reactivex.Single

interface PostsRepository {

    /**
     * Get the list of posts from the server
     *  @return [Single]  [List]  [PostDao] list of posts found
     */
    fun getPosts(): Single<List<PostDao>>

    /**
     * Get the list of comments given a post Id
     *  @param postId id of the post to load
     *  @return [Single]  [List]  [Comment] ist of comment found
     */
    fun getCommentsByPostId(postId: Int): Single<List<Comment>>
}
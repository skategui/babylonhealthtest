package guillaume.agis.babylonhealth.ui.detail

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.Post


// states possible of the  post detail view
sealed class PostDetailViewState {
    class RenderPost(val post: Post) : PostDetailViewState()
    class DisplayCommentsNb(val nbComment: Int) : PostDetailViewState()
    class DisplayError(val error: String? = "") : PostDetailViewState()
    class OpenEmail(val email: String) : PostDetailViewState()
    class SeeComments(val post: Post, val comments: List<Comment>) : PostDetailViewState()
    object NoInternet : PostDetailViewState()
}
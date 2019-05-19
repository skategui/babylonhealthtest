package guillaume.agis.babylonhealth.ui.detail

import guillaume.agis.babylonhealth.model.Comment
import guillaume.agis.babylonhealth.model.Post

data class PostDetailViewState(
    val action: Actions = Actions.None,
    val post: Post? = null,
    val comments: List<Comment> = emptyList()
)

sealed class Actions {
    class Error(val error: String? = "") : Actions()
    object NoInternet : Actions()
    object None : Actions()
    class OpenEmail(val email: String) : Actions()
    object SeeComments : Actions()
}
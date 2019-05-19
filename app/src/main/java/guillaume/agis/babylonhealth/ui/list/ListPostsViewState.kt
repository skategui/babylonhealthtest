package guillaume.agis.babylonhealth.ui.list

import guillaume.agis.babylonhealth.model.Post

data class ListPostsViewState(
    val action: Actions = Actions.None,
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList()
)

sealed class Actions {
    data class OpenPostDetail(val post: Post) : Actions()
    object NoInternet : Actions()
    class Error(val error: String? = "") : Actions()
    object None : Actions()
    object DisplayEmptyListMessage : Actions()
}
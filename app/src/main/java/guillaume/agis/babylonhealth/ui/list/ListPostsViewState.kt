package guillaume.agis.babylonhealth.ui.list

import guillaume.agis.babylonhealth.model.Post

// states possible of the list posts view
sealed class ListPostsViewState {
    class DisplayPostsList(val posts: List<Post>) : ListPostsViewState()
    class OpenPostDetail(val post: Post) : ListPostsViewState()
    class Error(val error: String? = "") : ListPostsViewState()
    object NoInternet : ListPostsViewState()
    object ShowLoading : ListPostsViewState()
    object DisplayEmptyListMessage : ListPostsViewState()
}
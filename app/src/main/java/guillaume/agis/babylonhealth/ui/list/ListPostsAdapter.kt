package guillaume.agis.babylonhealth.ui.list

import androidx.recyclerview.widget.RecyclerView
import guillaume.agis.babylonhealth.model.Post
import io.reactivex.Observable

abstract class ListPostsAdapter : RecyclerView.Adapter<ListPostsAdapterImpl.PostViewHolder>() {

    abstract fun update(list: List<Post>)

    abstract fun getClickRow(): Observable<Post>
}

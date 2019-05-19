package guillaume.agis.babylonhealth.ui.detail

import androidx.recyclerview.widget.RecyclerView
import guillaume.agis.babylonhealth.model.Comment


abstract class CommentsAdapter : RecyclerView.Adapter<CommentsAdapterImpl.PostViewHolder>() {

    abstract fun update(list: List<Comment>)
}

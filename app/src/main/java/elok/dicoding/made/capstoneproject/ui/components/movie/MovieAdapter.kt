package elok.dicoding.made.capstoneproject.ui.components.movie

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.core.databinding.ItemMovieBinding
import elok.dicoding.made.core.domain.model.Movie
import elok.dicoding.made.core.ui.base.BaseAdapter

class MovieAdapter : BaseAdapter<Movie, ItemMovieBinding>(DIFF_CALLBACK) {

    var shareListener: ((item: Movie) -> Unit)? = null

    override fun getLayout(): Int = R.layout.item_movie

    override fun onBindViewHolder(
        holder: BaseAdapter.Companion.BaseViewHolder<ItemMovieBinding>,
        position: Int
    ) {
        val item = getItem(position) ?: return
        holder.apply {
            binding.root.setOnClickListener { listener?.invoke(it, position, item) }
            binding.btnShare.setOnClickListener { shareListener?.invoke(item) }
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        shareListener = null
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
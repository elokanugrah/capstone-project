package elok.dicoding.made.capstoneproject.ui.components.tv

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.core.databinding.ItemMovieTvBinding
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BaseAdapter

class  TvAdapter : BaseAdapter<MovieTv, ItemMovieTvBinding>(DIFF_CALLBACK) {

    var shareListener: ((item: MovieTv) -> Unit)? = null

    override fun getLayout(): Int = R.layout.item_movie_tv

    override fun onBindViewHolder(holder: BaseAdapter.Companion.BaseViewHolder<ItemMovieTvBinding>, position: Int) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieTv>() {
            override fun areContentsTheSame(oldItem: MovieTv, newItem: MovieTv): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: MovieTv, newItem: MovieTv): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package elok.dicoding.made.favorites.ui

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.core.databinding.ItemMovieTvBinding
import elok.dicoding.made.core.domain.model.MovieTv
import elok.dicoding.made.core.ui.base.BasePagedListAdapter

class  FavoriteMovieAdapter : BasePagedListAdapter<MovieTv, ItemMovieTvBinding>(DIFF_CALLBACK) {

    var shareListener: ((item: MovieTv) -> Unit)? = null

    override fun getLayout(): Int = R.layout.item_movie_tv

    override fun onBindViewHolder(holder: BasePagedListAdapter.Companion.BaseViewHolder<ItemMovieTvBinding>, position: Int) {
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
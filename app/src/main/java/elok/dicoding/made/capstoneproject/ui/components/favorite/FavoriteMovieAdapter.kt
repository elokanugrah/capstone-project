package elok.dicoding.made.capstoneproject.ui.components.favorite

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.capstoneproject.core.domain.model.MovieTv
import elok.dicoding.made.capstoneproject.core.utils.ext.observe
import elok.dicoding.made.capstoneproject.databinding.ItemMovieTvBinding
import elok.dicoding.made.capstoneproject.ui.base.BaseAdapter
import elok.dicoding.made.capstoneproject.ui.base.BasePagedListAdapter

class  FavoriteMovieAdapter : BasePagedListAdapter<MovieTv, ItemMovieTvBinding>(DIFF_CALLBACK) {

    lateinit var viewModel: FavoriteViewModel
    lateinit var lifecycleOwner: LifecycleOwner

    var favoriteListener: ((item: MovieTv, isFavorite: Boolean) -> Unit)? = null
    var shareListener: ((item: MovieTv) -> Unit)? = null

    override fun getLayout(): Int = R.layout.item_movie_tv

    override fun onBindViewHolder(holder: BasePagedListAdapter.Companion.BaseViewHolder<ItemMovieTvBinding>, position: Int) {
        val item = getItem(position) ?: return
        holder.apply {
            binding.root.setOnClickListener { listener?.invoke(it, position, item) }
            lifecycleOwner.observe(viewModel.isFavorite(item)) { isFavorite ->
                binding.cbIsFav.setOnClickListener {
                    favoriteListener?.invoke(item, isFavorite)
                }
                binding.apply {
                    setVariable(BR.isFavorite, isFavorite)
                    executePendingBindings()
                }
            }
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
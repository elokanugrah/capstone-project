package elok.dicoding.made.capstoneproject.ui.components.moviedetail

import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import elok.dicoding.made.capstoneproject.R
import elok.dicoding.made.core.databinding.ItemProductionCompanyBinding
import elok.dicoding.made.core.domain.model.ProducationCompany
import elok.dicoding.made.core.ui.base.BaseAdapter

class ProductionCompanyAdapter :
    BaseAdapter<ProducationCompany, ItemProductionCompanyBinding>(DIFF_CALLBACK) {

    override fun getLayout(): Int = R.layout.item_production_company

    override fun onBindViewHolder(
        holder: BaseAdapter.Companion.BaseViewHolder<ItemProductionCompanyBinding>,
        position: Int
    ) {
        val item = getItem(position) ?: return
        holder.apply {
            binding.root.setOnClickListener { listener?.invoke(it, position, item) }
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProducationCompany>() {
            override fun areContentsTheSame(
                oldItem: ProducationCompany,
                newItem: ProducationCompany
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(
                oldItem: ProducationCompany,
                newItem: ProducationCompany
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
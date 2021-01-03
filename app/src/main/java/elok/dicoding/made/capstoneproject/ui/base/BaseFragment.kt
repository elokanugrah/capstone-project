package elok.dicoding.made.capstoneproject.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.di.AppComponent

abstract class BaseFragment<B : ViewBinding>(val viewBinder: (LayoutInflater) -> B) : Fragment() {

    protected lateinit var appComponent: AppComponent
    var binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinder(inflater).let {
            binding = it
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.onViewCreated(savedInstanceState)
        observeViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent = (requireActivity().application as MyApplication).appComponent
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected abstract fun B.onViewCreated(savedInstanceState: Bundle?)

    protected abstract fun observeViewModel()
}
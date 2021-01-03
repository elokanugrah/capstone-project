package elok.dicoding.made.capstoneproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import elok.dicoding.made.capstoneproject.MyApplication
import elok.dicoding.made.capstoneproject.di.AppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

abstract class BaseActivity<B : ViewBinding>(val viewBinder: (LayoutInflater) -> B) : AppCompatActivity() {

    protected open lateinit var appComponent: AppComponent
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MyApplication).appComponent
        super.onCreate(savedInstanceState)
        binding = viewBinder(layoutInflater)
        setContentView(binding.root)

        binding.onCreate(savedInstanceState)
        observeViewModel()
    }

    protected abstract fun B.onCreate(savedInstanceState: Bundle?)

    protected abstract fun observeViewModel()
}
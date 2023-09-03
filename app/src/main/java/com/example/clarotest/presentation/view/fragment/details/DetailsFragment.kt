package com.example.clarotest.presentation.view.fragment.details

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.clarotest.R
import com.example.clarotest.databinding.FragmentDetailsBinding
import com.example.clarotest.domain.models.EntryDetails

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private var entryDetails: EntryDetails? = null
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        webView = binding.webview

        arguments?.let {
            entryDetails = it.getParcelable(ARG_MY_ENTRY_OBJECT)!!
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildNavigation()

        when( resources.getBoolean(R.bool.isTablet)) {
            true -> {
                val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    showWebView()
                }
            }
            false -> {
                showWebView()
            }
        }

    }

    private fun buildNavigation() {
//        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
//        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
       // toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun showWebView() {
        binding.progressBar.visibility = View.VISIBLE
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }
        entryDetails?.let {
            webView.loadUrl(it.Link)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARG_MY_ENTRY_OBJECT, entryDetails)
    }

    fun loadUrl(url: String) {
        binding.progressBar.visibility = View.VISIBLE
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }
        webView.loadUrl(url)
    }

    companion object {
        const val ARG_MY_ENTRY_OBJECT = "my_entry_object"
    }
}
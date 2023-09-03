package com.example.clarotest.presentation.view.fragment.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clarotest.R
import com.example.clarotest.util.Resource
import com.example.clarotest.databinding.FragmentHomeBinding
import com.example.clarotest.domain.models.EntryDetails
import com.example.clarotest.presentation.view.adapter.EntryAdapter
import com.example.clarotest.presentation.view.fragment.details.DetailsFragment
import com.example.clarotest.presentation.view.fragment.details.DetailsFragment.Companion.ARG_MY_ENTRY_OBJECT
import com.example.clarotest.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), EntryAdapter.EntryItemListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var entryAdapter: EntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                entryAdapter.filter.filter((newText))
                return false
            }
        })
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item: MenuItem = menu.findItem(R.id.actionSearch)
        item.isVisible = true
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entries.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { animalList -> renderList(animalList) }
                            binding.entryRv.visibility = View.VISIBLE
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.entryRv.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.errorTxt.visibility = View.VISIBLE
                            binding.errorTxt.text = it.uiText?.asString(requireContext()) ?: getString(R.string.error_unknown)
                        }
                    }
                }
            }
        }
    }

    override fun onClickedItem(entry: EntryDetails) {
        when(resources.getBoolean(R.bool.isTablet)) {
            true -> {
                val orientation = resources.configuration.orientation
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    navigateToDetail(entry)
                } else {
                    val detailsFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.frameLayout2) as DetailsFragment
                    detailsFragment.loadUrl(entry.Link)
                }

            } false -> {
                navigateToDetail(entry)
            }
        }
    }

    private fun navigateToDetail(entry: EntryDetails) {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailsFragment,
            bundleOf(ARG_MY_ENTRY_OBJECT to entry)
        )
    }

    private fun renderList(entries: List<EntryDetails>) {
        entryAdapter.addData(entries)
    }

    private fun setupUI() {
        setHasOptionsMenu(true)

        binding.entryRv.apply {
            layoutManager = LinearLayoutManager(
                context,
            )
            setHasFixedSize(true)
        }
        entryAdapter = EntryAdapter(this, arrayListOf())
        binding.entryRv.adapter = entryAdapter
    }

}
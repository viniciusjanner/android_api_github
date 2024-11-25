package com.viniciusjanner.apigithub.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.viniciusjanner.apigithub.R
import com.viniciusjanner.apigithub.databinding.FragmentRepoListBinding
import com.viniciusjanner.apigithub.framework.imageloader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoListFragment : Fragment(R.layout.fragment_repo_list) {

    private var _binding: FragmentRepoListBinding? = null
    private val binding get() = _binding!!

    private val repoListViewModel: RepoListViewModel by viewModels()

    private val repoListAdapter: RepoListAdapter by lazy {
        RepoListAdapter(imageLoader) { itemRepoListResponse ->
            val action = RepoListFragmentDirections.actionRepoListFragmentToRepoPullRequestFragment(
                repoName = itemRepoListResponse.name ?: "",
                repoUserName = itemRepoListResponse.userName ?: ""
            )
            findNavController().navigate(action)
        }
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponents()
        initListeners()
        initObservers()
        fetchData()
    }

    private fun initComponents() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoListAdapter
        }
    }

    private fun initListeners() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchData()
        }

        binding.retryButton.setOnClickListener {
            fetchData()
        }
    }

    private fun initObservers() {
        repoListViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            }
        })

        repoListViewModel.repositoriesLiveData.observe(viewLifecycleOwner, Observer { pagingData ->
            repoListAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            showRecyclerView()
        })

        repoListViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            if (error != null) {
                showError()
            }
        })
    }

    private fun fetchData() {
        repoListViewModel.fetchPopularJavaRepositories()
    }

    private fun showLoading() {
        binding.viewFlipper.displayedChild = 0
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showRecyclerView() {
        binding.viewFlipper.displayedChild = 1
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun showError() {
        binding.viewFlipper.displayedChild = 2
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

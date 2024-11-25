package com.viniciusjanner.apigithub.presentation.pullrequest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.viniciusjanner.apigithub.R
import com.viniciusjanner.apigithub.databinding.FragmentRepoPullRequestBinding
import com.viniciusjanner.apigithub.framework.imageloader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RepoPullRequestFragment : Fragment(R.layout.fragment_repo_pull_request) {

    private var _binding: FragmentRepoPullRequestBinding? = null
    private val binding get() = _binding!!

    private var repoName: String? = null
    private var repoUserName: String? = null

    private val repoPullRequestViewModel: RepoPullRequestViewModel by viewModels()

    private val repoPullRequestAdapter: RepoPullRequestAdapter by lazy {
        RepoPullRequestAdapter(imageLoader)
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoPullRequestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArgs()
        initComponents()
        initListeners()
        initObservers()
        fetchData()
    }

    private fun initArgs() {
        arguments?.let {
            repoName = it.getString("repoName", null)
            repoUserName = it.getString("repoUserName", null)
        }
    }

    private fun initComponents() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoPullRequestAdapter
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
        repoPullRequestViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                showLoading()
            }
        })

        repoPullRequestViewModel.pullRequestsLiveData.observe(viewLifecycleOwner, Observer { pagingData ->
            repoPullRequestAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
            showRecyclerView()
        })

        repoPullRequestViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            if (error != null) {
                showError()
            }
        })
    }

    private fun fetchData() {
        if (isValidArgs()) {
            repoPullRequestViewModel.fetchPullRequests(owner = repoUserName!!, repoName = repoName!!)
        } else {
            showError()
        }
    }

    private fun isValidArgs(): Boolean {
        return repoUserName != null && repoUserName!!.isNotEmpty() &&
                repoName != null && repoName!!.isNotEmpty()
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

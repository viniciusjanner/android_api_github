package com.viniciusjanner.apigithub.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

        }
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoListAdapter
        }

        repoListViewModel.repositoriesLiveData.observe(viewLifecycleOwner, Observer { pagingData ->
            repoListAdapter.submitData(viewLifecycleOwner.lifecycle, pagingData)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

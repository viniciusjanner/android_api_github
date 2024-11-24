package com.viniciusjanner.apigithub.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.viniciusjanner.apigithub.core.domain.model.ItemRepoModel
import com.viniciusjanner.apigithub.databinding.ItemRepoListBinding
import com.viniciusjanner.apigithub.framework.imageloader.ImageLoader

class RepoListAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (ItemRepoModel) -> Unit
) : PagingDataAdapter<ItemRepoModel, RepoListAdapter.RepoListViewHolder>(DIFF_CALLBACK) {

    inner class RepoListViewHolder(private val binding: ItemRepoListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemRepoModel?) {
            item?.let { repo ->
                with(binding) {
                    repoName.text = repo.name
                    repoDescription.text = repo.description
                    repoForkCount.text = "${repo.forksCount}"
                    repoStarCount.text = "${repo.stargazersCount}"
                    repoUserName.text = repo.userName

                    imageLoader.load(repoAvatar, repo.avatarUrl ?: "")

                    root.setOnClickListener { onClick(repo) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val binding = ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemRepoModel>() {
            override fun areItemsTheSame(oldItem: ItemRepoModel, newItem: ItemRepoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ItemRepoModel, newItem: ItemRepoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

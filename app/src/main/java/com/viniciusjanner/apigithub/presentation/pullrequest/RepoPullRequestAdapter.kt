package com.viniciusjanner.apigithub.presentation.pullrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.viniciusjanner.apigithub.databinding.ItemRepoPullRequestBinding
import com.viniciusjanner.apigithub.framework.imageloader.ImageLoader
import com.viniciusjanner.apigithub.utils.toBrazilianDate
import com.viniciusjanner.domain.model.PullRequestModel

class RepoPullRequestAdapter(
    private val imageLoader: ImageLoader
) : PagingDataAdapter<PullRequestModel, RepoPullRequestAdapter.RepoPullRequestViewHolder>(DIFF_CALLBACK) {

    inner class RepoPullRequestViewHolder(private val binding: ItemRepoPullRequestBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PullRequestModel?) {
            item?.let { pr ->
                with(binding) {
                    prDate.text = pr.dateCreated?.toBrazilianDate() ?: ""
                    prTitle.text = pr.title
                    prBody.text = pr.body
                    prUserName.text = pr.userName
                    prState.text = pr.state

                    imageLoader.load(prAvatar, pr.userAvatarUrl ?: "")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoPullRequestViewHolder {
        val binding = ItemRepoPullRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoPullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoPullRequestViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PullRequestModel>() {
            override fun areItemsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PullRequestModel, newItem: PullRequestModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}

package com.arlanov.githubclient.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arlanov.githubclient.R
import com.arlanov.githubclient.api.model.GitHubRepo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class GitHubRepoAdapter(val listener: (GitHubRepo)-> Unit) : RecyclerView.Adapter<GitHubRepoAdapter.MyViewHolder>() {

    private lateinit var list: List<GitHubRepo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount() = list.size

    fun updateData(list: List<GitHubRepo>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(rep: GitHubRepo, listener: (GitHubRepo) -> Unit){
            itemView.setOnClickListener{
                listener.invoke(rep)
            }
            showText(rep.name)
        }
        private fun showText(s: String?){
            itemText.text = s
        }
    }
}
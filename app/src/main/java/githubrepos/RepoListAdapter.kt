package githubrepos

import githubrepos.data.GitHubRepository
import githubrepos.presentation.KEY_URL
import githubrepos.presentation.WebViewActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleappbasic.R

class RepoListAdapter(private var repositoryList: ArrayList<GitHubRepository> = ArrayList()) : RecyclerView.Adapter<RepoListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }

    fun updateRepoList(repoList: ArrayList<GitHubRepository>) {
        repositoryList = repoList
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: GitHubRepository){
            itemView.findViewById<TextView>(R.id.content_org_name).text = repository.name

            val intent = Intent(itemView.context, WebViewActivity::class.java)
            intent.putExtra(KEY_URL, repository.html_url)
            itemView.setOnClickListener { itemView.context.startActivity(intent) }
        }
    }
}
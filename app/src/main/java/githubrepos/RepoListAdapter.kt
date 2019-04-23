package githubrepos

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleappbasic.R
import githubrepos.data.GitHubRepository
import githubrepos.presentation.KEY_URL
import githubrepos.presentation.WebViewActivity
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoListAdapter(private var repositoryList: List<GitHubRepository> = emptyList()) :
    RecyclerView.Adapter<RepoListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])
    }

    fun updateRepoList(repoList: List<GitHubRepository>) {
        repositoryList = repoList
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repository: GitHubRepository) {
            itemView.apply {
                text_org_name.text = repository.name
                text_repo_desc.text = repository.description
                text_stars.text =
                    String.format(context.getString(R.string.repo_star), repository.stargazers_count.toString())
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WebViewActivity::class.java)
                intent.putExtra(KEY_URL, repository.html_url)
                itemView.context.startActivity(intent)
            }
        }
    }
}
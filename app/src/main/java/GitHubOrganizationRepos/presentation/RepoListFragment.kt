package GitHubOrganizationRepos.presentation

import GitHubOrganizationRepos.RepoListAdapter
import GitHubOrganizationRepos.domain.GitHubOrganizationReposUseCase
import GitHubOrganizationRepos.domain.GitHubRepoListRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleappbasic.R

class RepoListFragment : Fragment() {

    lateinit var viewModel: RepoListViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        init()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        //todo Dagger
        viewModel = ViewModelProviders.of(
            this,
            EmployeeListViewModelFactory(GitHubOrganizationReposUseCase(GitHubRepoListRepository()))
        ).get(RepoListViewModel::class.java)

        viewModel.getOrganizationRepos()
            .subscribe { repos ->
                recyclerView.adapter = RepoListAdapter(repos)
            }
    }
}
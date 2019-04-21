package GitHubOrganizationRepos.presentation

import GitHubOrganizationRepos.RepoListAdapter
import GitHubOrganizationRepos.data.RepoListState
import GitHubOrganizationRepos.domain.GitHubOrganizationReposUseCase
import GitHubOrganizationRepos.domain.GitHubRepoListRepository
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleappbasic.R
import kotlinx.android.synthetic.main.fragment_list.*

class RepoListFragment : Fragment() {
    //todo 1. Empty view
    //todo 2. Loading spinner
    //todo 3. Error Handling
    //todo 4.1 Adding Welcome Fragment
    //todo 4.2 Clicking to load via a webView
    //todo 5. Visual (divider to recycler view, appbar, ...etc)
    //todo 6. dagger
    //todo 7. resources disposable of RxJava
    //todo 8. Test
    //todo 9. ConstraintLayout
    //todo 10. Write some comments
    private lateinit var viewModel: RepoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopThreeOrganizationRepos()
    }

    private fun init() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = RepoListAdapter()

        //todo Dagger
        viewModel = ViewModelProviders.of(
            this,
            EmployeeListViewModelFactory(GitHubOrganizationReposUseCase(GitHubRepoListRepository()))
        ).get(RepoListViewModel::class.java)

        viewModel.getRepoList().observe(this,
            Observer<RepoListState> { t ->
                renderRepoList(t)
            })
    }

    /*
    * We use a state sealed class to represent different stage of our action.
    * Which also allows us to render based on loading and error handling
    * while observing the the LiveData from the ViewModel.
    * */
    private fun renderRepoList(state: RepoListState) {
        when (state) {
            is RepoListState.Loading -> renderRepoListLoading()

            is RepoListState.Loaded -> renderRepoListLoaded(state)

            is RepoListState.Error -> renderRepoListError(state.error)
        }
    }

    private fun renderRepoListLoading() {
        progress_spinner.visibility = View.VISIBLE
        empty_view.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun renderRepoListLoaded(state: RepoListState.Loaded) {
        progress_spinner.visibility = View.GONE

        when (state.repoList.isEmpty()) {
            true -> {
                empty_view.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            false -> {
                empty_view.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                (recyclerView.adapter as RepoListAdapter).updateRepoList(state.repoList)
            }
        }


    }

    private fun renderRepoListError(errer: Throwable) {
        progress_spinner.visibility = View.GONE
        recyclerView.visibility = View.GONE
        Log.d("ERROR", "ERROR")
    }
}
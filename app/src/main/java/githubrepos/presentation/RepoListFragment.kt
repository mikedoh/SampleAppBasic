package githubrepos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleappbasic.R
import githubrepos.GitHubRepoApplication
import githubrepos.RepoListAdapter
import githubrepos.data.RepoListState
import kotlinx.android.synthetic.main.fragment_list.*
import timber.log.Timber
import javax.inject.Inject

const val KEY_ORG_NAME = "KEY_ORG_NAME"

class RepoListFragment internal constructor() : Fragment() {
    //todo 1. Empty view
    //todo 2. Loading spinner
    //todo 3. Error Handling
    //todo 4.1 Adding Welcome Fragment
    //todo 4.2 Clicking to load via a webView
    //todo 5. Visual (divider to recycler view, appbar, ...etc), text size
    //todo 6. dagger
    //todo 7. resources disposable of RxJava
    //todo 8. Test
    //todo 9. ConstraintLayout
    //todo 10. Write some comments
    private lateinit var viewModel: RepoListViewModel

    @Inject
    lateinit var viewModelFactory: GitHubRepoListViewModelFactory

    companion object {
        fun getInstance(orgName: String): RepoListFragment {
            val fragment = RepoListFragment()
            val bundle = Bundle()
            bundle.putString(KEY_ORG_NAME, orgName)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GitHubRepoApplication.appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTopThreeOrganizationRepos(arguments?.getString(KEY_ORG_NAME) ?: "")
    }

    private fun init() {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = RepoListAdapter()
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
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

    private fun renderRepoListError(error: Throwable) {
        progress_spinner.visibility = View.GONE
        recyclerView.visibility = View.GONE

        Toast.makeText(activity, getString(R.string.error_message), Toast.LENGTH_LONG).show()

        Timber.d(error)

        fragmentManager?.popBackStack()
    }
}
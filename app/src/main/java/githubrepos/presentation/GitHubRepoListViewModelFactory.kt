package githubrepos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class GitHubRepoListViewModelFactory @Inject constructor(val viewmodel: RepoListViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewmodel as T
    }

}
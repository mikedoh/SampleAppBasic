package githubrepos.data

/*
* A sealed class which represents different states
* of the action performed by the RepoListViewModel.
* They will be emitted by the LiveData of the RepoListViewModel
* and the view (in this case RepoListFragment) will render based on the value.
* */
sealed class RepoListState {
    object Loading : RepoListState()
    data class Loaded(val repoList: List<GitHubRepository> = emptyList()) : RepoListState()
    data class Error(val error: Throwable) : RepoListState()
}
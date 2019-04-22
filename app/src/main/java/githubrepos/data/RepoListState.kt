package githubrepos.data

sealed class RepoListState {
    object Loading : RepoListState()
    data class Loaded(val repoList: ArrayList<GitHubRepository> = ArrayList()) : RepoListState()
    data class Error(val error: Throwable) : RepoListState()
}
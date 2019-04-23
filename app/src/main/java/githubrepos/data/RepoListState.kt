package githubrepos.data

sealed class RepoListState {
    object Loading : RepoListState()
    data class Loaded(val repoList: List<GitHubRepository> = emptyList()) : RepoListState()
    data class Error(val error: Throwable) : RepoListState()
}
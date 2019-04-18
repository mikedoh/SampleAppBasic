package GitHubOrganizationRepos.domain

class GitHubOrganizationReposUseCase(val repository: GitHubRepoListRepository) {
    fun getOrganizationRepos() = repository.getOrganizatinoRepos()
}
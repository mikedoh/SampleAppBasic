package GitHubOrganizationRepos.domain

const val NUMBER_OF_REPOS_DISPLAY = 3

class GitHubOrganizationReposUseCase(val repository: GitHubRepoListRepository) {

    fun getOrganizationRepos(orgName: String) = repository.getOrganizatinoRepos(orgName)
}
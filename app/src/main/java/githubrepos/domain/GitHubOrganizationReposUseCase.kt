package githubrepos.domain

import javax.inject.Inject

const val NUMBER_OF_REPOS_DISPLAY = 3

class GitHubOrganizationReposUseCase @Inject constructor(val repository: GitHubRepoListRepository) {

    fun getOrganizationRepos(orgName: String) = repository.getOrganizatinoRepos(orgName)
}
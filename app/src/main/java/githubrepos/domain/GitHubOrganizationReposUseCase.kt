package githubrepos.domain

import javax.inject.Inject

const val NUMBER_OF_REPOS_DISPLAY = 3

class GitHubOrganizationReposUseCase @Inject constructor(val repository: GitHubApiRepository) {

    fun getOrganizationRepos(orgName: String) = repository.getOrganizationRepoList(orgName)
}
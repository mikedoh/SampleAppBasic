package GitHubOrganizationRepos.presentation

import androidx.lifecycle.ViewModel
import GitHubOrganizationRepos.domain.GitHubOrganizationReposUseCase

class RepoListViewModel(val useCase: GitHubOrganizationReposUseCase) : ViewModel() {
    fun getOrganizationRepos() = useCase.getOrganizationRepos()
}
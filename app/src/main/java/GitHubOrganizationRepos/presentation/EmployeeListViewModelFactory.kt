package GitHubOrganizationRepos.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import GitHubOrganizationRepos.domain.GitHubOrganizationReposUseCase

class EmployeeListViewModelFactory(val useCase: GitHubOrganizationReposUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoListViewModel(useCase) as T
    }

}
package GitHubOrganizationRepos.presentation

import GitHubOrganizationRepos.data.GitHubRepository
import GitHubOrganizationRepos.data.RepoListState
import GitHubOrganizationRepos.domain.GitHubOrganizationReposUseCase
import GitHubOrganizationRepos.domain.NUMBER_OF_REPOS_DISPLAY
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

class RepoListViewModel(val useCase: GitHubOrganizationReposUseCase) : ViewModel() {

    private val repoList: MutableLiveData<RepoListState> = MutableLiveData()

    fun getRepoList(): LiveData<RepoListState> = repoList

    fun getTopThreeOrganizationRepos(): Disposable {

        return useCase.getOrganizationRepos()
            .doOnSubscribe {repoList.value = RepoListState.Loading}
            .map {
                it.apply {
                    sortWith(object : Comparator<GitHubRepository> {
                        override fun compare(o1: GitHubRepository, o2: GitHubRepository): Int {
                            return o2.stargazers_count - o1.stargazers_count
                        }
                    })
                }
                ArrayList(it.subList(0, NUMBER_OF_REPOS_DISPLAY))
            }.subscribe(
                { result -> repoList.value = RepoListState.Loaded(result) },
                { error -> repoList.value = RepoListState.Error(error) })
    }

}
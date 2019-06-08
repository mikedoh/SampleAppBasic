package githubrepos.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import githubrepos.GitHubRepoApplication
import githubrepos.data.GitHubRepository
import githubrepos.data.RepoListState
import githubrepos.domain.GitHubOrganizationReposUseCase
import githubrepos.domain.NUMBER_OF_REPOS_DISPLAY
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RepoListViewModel : ViewModel() {

    init {
        GitHubRepoApplication.appComponent.inject(this)
    }

    @Inject
    lateinit var useCase: GitHubOrganizationReposUseCase

    private val repoList: MutableLiveData<RepoListState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    // expose Livedata as immutable
    fun getRepoList(): LiveData<RepoListState> = repoList

    fun getTopThreeReposByStars(orgName: String): Disposable {

        val disposable = useCase
            .getOrganizationRepos(orgName)
            .doOnSubscribe { repoList.value = RepoListState.Loading }
            .map { mapInitialResult(it) }.subscribe(
                { result -> repoList.value = RepoListState.Loaded(result) },
                { error -> repoList.value = RepoListState.Error(error) })

        compositeDisposable.add(disposable)

        return disposable
    }

    private fun mapInitialResult(list: List<GitHubRepository>): List<GitHubRepository> {
        return sortRepoListWithStars(list).subList(0, Math.min(list.size, NUMBER_OF_REPOS_DISPLAY))
    }

    private fun sortRepoListWithStars(list: List<GitHubRepository>): List<GitHubRepository> {
        return list.sortedWith(object : Comparator<GitHubRepository> {
            override fun compare(o1: GitHubRepository, o2: GitHubRepository): Int {
                return o2.stargazers_count - o1.stargazers_count
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
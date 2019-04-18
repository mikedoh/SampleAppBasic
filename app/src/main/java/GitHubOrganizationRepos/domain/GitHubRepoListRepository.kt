package GitHubOrganizationRepos.domain

import GitHubOrganizationRepos.network.RetrofitClient
import GitHubOrganizationRepos.network.GitHubOrganizationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

val service = lazy { RetrofitClient.instance.create(GitHubOrganizationService::class.java) }

class GitHubRepoListRepository {
    //todo Make organization name parameters dynamic
    //todo should we do subscribeOn and observeOn here? try to google
    fun getOrganizatinoRepos() =
        service.value.getOrganizationRepos("nytimes")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
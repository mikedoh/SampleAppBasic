package GitHubOrganizationRepos.domain

import GitHubOrganizationRepos.network.RetrofitClient
import GitHubOrganizationRepos.network.GitHubOrganizationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

val service = lazy { RetrofitClient.instance.create(GitHubOrganizationService::class.java) }

class GitHubRepoListRepository {
    //todo should we do subscribeOn and observeOn here? try to google
    fun getOrganizatinoRepos(orgName: String) =
        service.value.getOrganizationRepos(orgName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
package githubrepos.domain

import githubrepos.network.GitHubOrganizationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GitHubRepoListRepository @Inject constructor(val service: GitHubOrganizationService) {

    //todo should we do subscribeOn and observeOn here? try to google
    fun getOrganizatinoRepos(orgName: String) =
        service.getOrganizationRepos(orgName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
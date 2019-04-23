package githubrepos.domain

import githubrepos.network.GitHubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GitHubApiRepository @Inject constructor(val service: GitHubService) {

    //todo should we do subscribeOn and observeOn here? try to google
    fun getOrganizationRepoList(orgName: String) =
        service.getOrganizationRepoList(orgName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
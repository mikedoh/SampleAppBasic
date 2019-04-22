package githubrepos.network

import githubrepos.data.GitHubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubOrganizationService {
    @GET("orgs/{name}/repos")
    fun getOrganizationRepos(@Path("name") organization: String): Single<ArrayList<GitHubRepository>>
}

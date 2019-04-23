package githubrepos.network

import githubrepos.data.GitHubRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("orgs/{name}/repos")
    fun getOrganizationRepoList(@Path("name") organization: String): Single<List<GitHubRepository>>
}

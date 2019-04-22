package githubrepos.di

import dagger.Component
import githubrepos.domain.GitHubRepoListRepository
import githubrepos.presentation.RepoListFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(gitHubRepoListRepository: GitHubRepoListRepository)
    fun inject(RepoListFragment: RepoListFragment)
}
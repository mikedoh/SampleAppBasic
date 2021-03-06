package githubrepos.di

import dagger.Component
import githubrepos.domain.GitHubApiRepository
import githubrepos.presentation.RepoListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(gitHubApiRepository: GitHubApiRepository)
    fun inject(viewModel: RepoListViewModel)
}
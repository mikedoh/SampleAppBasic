package githubrepos.di

import dagger.Module
import dagger.Provides
import githubrepos.network.GitHubOrganizationService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideGitHubOrganizationRepoService(): Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Inject
    fun provideOrganizationRepoService(client: Retrofit): GitHubOrganizationService {
        return  client.create(GitHubOrganizationService::class.java)
    }
}
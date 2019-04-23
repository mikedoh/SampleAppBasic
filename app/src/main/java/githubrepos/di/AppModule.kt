package githubrepos.di

import dagger.Module
import dagger.Provides
import githubrepos.network.GitHubService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideGitHubRetrofitClient(): Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Inject
    fun provideGitHubService(client: Retrofit): GitHubService {
        return  client.create(GitHubService::class.java)
    }
}
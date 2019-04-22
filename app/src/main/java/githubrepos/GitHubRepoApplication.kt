package githubrepos

import android.app.Application
import githubrepos.di.AppComponent
import githubrepos.di.AppModule
import githubrepos.di.DaggerAppComponent

class GitHubRepoApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent =
            DaggerAppComponent
                .builder()
                .appModule(AppModule())
                .build()
    }
}
package GitHubOrganizationRepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleappbasic.R

class MainActivity : AppCompatActivity(), MainFragment.HasRepositoryListPage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //todo dagger
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, MainFragment(), null)
            .commit()

        setSupportActionBar(findViewById(R.id.toolbar))

        //todo put in the strings.xml
        supportActionBar?.title = "Organization Repositories"
    }

    //todo dagger
    override fun goToRepositoryListPage(orgName: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, RepoListFragment(), null)
            .addToBackStack(null)
            .commit()
    }

}

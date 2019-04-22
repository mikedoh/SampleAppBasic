package GitHubOrganizationRepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleappbasic.R

class MainActivity : AppCompatActivity(), WelcomePageFragment.HasRepositoryListPage {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, WelcomePageFragment(), null)
            .commit()

        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.title = getString(R.string.tool_bar_title)
    }

    override fun goToRepositoryListPage(orgName: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, RepoListFragment.getInstance(orgName), null)
            .addToBackStack(null)
            .commit()
    }

}

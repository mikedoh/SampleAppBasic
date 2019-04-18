package GitHubOrganizationRepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleappbasic.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //todo dagger
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, RepoListFragment(), null)
            .addToBackStack(null)
            .commit()

//        supportActionBar.title = "Organization Repositories"
    }


}

package GitHubOrganizationRepos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleappbasic.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_enter_org_name.setOnClickListener {
            (activity as HasRepositoryListPage).goToRepositoryListPage(edit_text.toString())
        }
    }

    interface HasRepositoryListPage {
        fun goToRepositoryListPage(orgName: String)
    }
}
package githubrepos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleappbasic.R
import kotlinx.android.synthetic.main.fragment_welcome_page.*

class WelcomePageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_enter_org_name.setOnClickListener {
            (activity as HasRepositoryListPage).goToRepositoryListPage(edit_text.text.toString())
        }
    }

    interface HasRepositoryListPage {
        fun goToRepositoryListPage(orgName: String)
    }
}
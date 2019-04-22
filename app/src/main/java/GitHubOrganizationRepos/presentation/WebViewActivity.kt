package GitHubOrganizationRepos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleappbasic.R
import kotlinx.android.synthetic.main.activity_webview.*

const val KEY_URL = "KEY_URL"

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_webview)

        web_view.loadUrl(intent.extras?.getString(KEY_URL, ""))
        web_view.settings.javaScriptEnabled = true
    }
}
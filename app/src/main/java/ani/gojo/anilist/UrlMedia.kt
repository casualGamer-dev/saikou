package ani.gojo.anilist

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ani.gojo.loadIsMAL
import ani.gojo.loadMedia
import ani.gojo.logError
import ani.gojo.startMainActivity

class UrlMedia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data: Uri? = intent?.data
        if (data?.host != "anilist.co") loadIsMAL = true
        try {
            if (data?.pathSegments?.get(1) != null) loadMedia = data.pathSegments?.get(1)?.toIntOrNull()
        } catch (e: Exception) {
            logError(e)
        }
        startMainActivity(this)
    }
}
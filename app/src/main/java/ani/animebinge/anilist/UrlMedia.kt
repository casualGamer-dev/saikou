package ani.animebinge.anilist

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ani.animebinge.loadIsMAL
import ani.animebinge.loadMedia
import ani.animebinge.logError
import ani.animebinge.startMainActivity

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
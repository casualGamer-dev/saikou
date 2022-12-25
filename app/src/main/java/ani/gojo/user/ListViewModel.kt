package ani.gojo.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ani.gojo.anilist.Anilist
import ani.gojo.loadData
import ani.gojo.media.Media
import ani.gojo.tryWithSuspend

class ListViewModel : ViewModel() {
    var grid = MutableLiveData(loadData<Boolean>("listGrid") ?: true)

    private val lists = MutableLiveData<MutableMap<String, ArrayList<Media>>>()
    fun getLists(): LiveData<MutableMap<String, ArrayList<Media>>> = lists
    suspend fun loadLists(anime: Boolean, userId: Int, sortOrder: String? = null) {
        tryWithSuspend {
            lists.postValue(Anilist.query.getMediaLists(anime, userId, sortOrder))
        }
    }
}
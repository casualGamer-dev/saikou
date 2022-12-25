package ani.animebinge.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ani.animebinge.anilist.Anilist
import ani.animebinge.loadData
import ani.animebinge.media.Media
import ani.animebinge.tryWithSuspend

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
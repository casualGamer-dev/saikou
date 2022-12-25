package ani.animebinge.anime

import ani.animebinge.media.MediaDetailsViewModel
import ani.animebinge.media.SourceAdapter
import ani.animebinge.media.SourceSearchDialogFragment
import ani.animebinge.parsers.ShowResponse
import kotlinx.coroutines.CoroutineScope

class AnimeSourceAdapter(
    sources: List<ShowResponse>,
    val model: MediaDetailsViewModel,
    val i: Int,
    val id: Int,
    fragment: SourceSearchDialogFragment,
    scope: CoroutineScope
) : SourceAdapter(sources, fragment, scope) {

    override suspend fun onItemClick(source: ShowResponse) {
        model.overrideEpisodes(i, source, id)
    }
}
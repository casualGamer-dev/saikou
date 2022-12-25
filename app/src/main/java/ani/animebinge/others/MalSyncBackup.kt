package ani.animebinge.others

import ani.animebinge.client
import ani.animebinge.parsers.ShowResponse
import ani.animebinge.tryWithSuspend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object MalSyncBackup {
    @Serializable
    data class MalBackUpSync(
        @SerialName("Pages") val pages: Map<String, Map<String, Page>>? = null
    )

    @Serializable
    data class Page(
        val identifier: String,
        val title: String,
        val url: String? = null,
        val image: String? = null,
        val active: Boolean? = null,
    )

    suspend fun get(id: Int, name: String, dub: Boolean = false): ShowResponse? {
        return tryWithSuspend {
            val json =
                client.get("https://raw.githubusercontent.com/MALSync/MAL-Sync-Backup/master/data/anilist/anime/$id.json")
            if (json.text != "404: Not Found")
                json.parsed<MalBackUpSync>().pages?.get(name)?.forEach {
                    val page = it.value
                    val isDub = page.title.lowercase().replace(" ", "").endsWith("(dub)")
                    val slug = if (dub == isDub) page.identifier else null
                    if (slug != null && page.active == true && page.url != null) {
                        val url = when(name){
                            "9anime" -> page.url.replace("9anime.to","9anime.id")
                            "Gogoanime"  -> slug
                            else -> page.url
                        }
                        return@tryWithSuspend ShowResponse(page.title, url, page.image ?: "")
                    }
                }
            return@tryWithSuspend null
        }
    }
}
package ani.gojo.parsers.anime.extractors

import ani.gojo.FileUrl
import ani.gojo.client
import ani.gojo.getSize
import ani.gojo.parsers.*

class StreamTape(override val server: VideoServer) : VideoExtractor() {
    private val linkRegex = Regex("""'robotlink'\)\.innerHTML = '(.+?)'\+ \('(.+?)'\)""")

    override suspend fun extract(): VideoContainer {
        val reg = linkRegex.find(client.get(server.embed.url).text)?:return VideoContainer(listOf())
        val extractedUrl = FileUrl("https:${reg.groups[1]!!.value + reg.groups[2]!!.value.substring(3)}")
        return VideoContainer(listOf(Video(null, VideoType.CONTAINER, extractedUrl, getSize(extractedUrl))))
    }
}
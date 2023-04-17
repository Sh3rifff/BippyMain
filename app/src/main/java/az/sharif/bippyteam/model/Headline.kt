package az.sharif.bippyteam.model

data class Headline(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
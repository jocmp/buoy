package com.jocmp.readeckclient

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bookmark(
    val id: String,
    val href: String,
    val created: String,
    val updated: String,
    val state: Int,
    val loaded: Boolean,
    val url: String,
    val title: String,
    val site_name: String,
    val site: String,
    val published: String,
    val authors: List<String>,
    val lang: String,
    val text_direction: String,
    val document_type: String,
    val type: String,
    val has_article: Boolean,
    val description: String,
    val is_deleted: Boolean,
    val is_marked: Boolean,
    val is_archived: Boolean,
    val labels: List<String>,
    val read_progress: Int,
    val resources: Resources,
    val word_count: Int,
    val reading_time: Int
) {
    @JsonClass(generateAdapter = true)
    data class Resources(
        val article: ResourceLink,
        val icon: ResourceImage,
        val image: ResourceImage,
        val log: ResourceLink,
        val props: ResourceLink,
        val thumbnail: ResourceImage
    )
    
    @JsonClass(generateAdapter = true)
    data class ResourceLink(
        val src: String
    )
    
    @JsonClass(generateAdapter = true)
    data class ResourceImage(
        val src: String,
        val width: Int,
        val height: Int
    )
}

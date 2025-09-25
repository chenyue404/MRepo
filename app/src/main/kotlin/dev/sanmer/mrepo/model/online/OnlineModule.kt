package dev.sanmer.mrepo.model.online

import android.annotation.SuppressLint
import dev.sanmer.mrepo.utils.StrUtil
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class OnlineModule(
    val id: String,
    val name: String,
    val version: String,
//    @SerialName("version_code")
    val versionCode: Int,
    val author: String,
    val description: String = "",
    val versions: List<VersionItem>,
    val readme: String = "",
    val track: Track = Track(),
) {
    val versionDisplay by lazy {
        StrUtil.getVersionDisplay(version, versionCode)
    }

    @Serializable
    data class Track(
        val type: String = "",
        val homepage: String = "",
        val source: String = "",
        val license: String = "",
        val donate: String = "",
        val support: String = ""
    )

    companion object {
        fun example() = OnlineModule(
            id = "online_example",
            name = "Example",
            version = "2022.08.16",
            versionCode = 1703,
            author = "Sanmer",
            description = "This is an example!",
            track = Track(
                license = "GPL-3.0"
            ),
            versions = emptyList()
        )
    }
}
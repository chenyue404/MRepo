package dev.sanmer.mrepo.database.entity.online

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import dev.sanmer.mrepo.model.online.OnlineModule
import dev.sanmer.mrepo.model.online.VersionItem

@Entity(
    tableName = "online",
    primaryKeys = ["id", "repo_url"]
)
data class OnlineModuleEntity(
    @ColumnInfo(name = "repo_url")
    val repoUrl: String,
    val id: String,
    val name: String,
    val version: String,
    @ColumnInfo(name = "version_code")
    val versionCode: Int,
    val author: String,
    val description: String = "",
    val readme: String = "",
    @Embedded
    val track: Track,
) {
    constructor(
        repoUrl: String,
        original: OnlineModule
    ) : this(
        repoUrl = repoUrl,
        id = original.id,
        name = original.name,
        version = original.version,
        versionCode = original.versionCode,
        author = original.author,
        description = original.description,
        readme = original.readme,
        track = Track(original.track)
    )

    fun toJson(versions: List<VersionItem> = emptyList()) = OnlineModule(
        id = id,
        name = name,
        version = version,
        versionCode = versionCode,
        author = author,
        description = description,
        versions = versions,
        readme = readme,
        track = track.toJson(),
    )

    data class Track(
        val type: String = "",
        val homepage: String = "",
        val source: String = "",
        val license: String = "",
        val donate: String = "",
        val support: String = ""
    ) {
        constructor(original: OnlineModule.Track) : this(
            type = original.type,
            homepage = original.homepage,
            source = original.source,
            license = original.license,
            donate = original.donate,
            support = original.support,
        )

        fun toJson() = OnlineModule.Track(
            type = type,
            homepage = homepage,
            source = source,
            license = license,
            donate = donate,
            support = support,
        )
    }
}
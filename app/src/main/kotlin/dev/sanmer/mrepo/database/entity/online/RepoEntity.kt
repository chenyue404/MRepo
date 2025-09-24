package dev.sanmer.mrepo.database.entity.online

import androidx.room.Embedded
import androidx.room.Entity
import dev.sanmer.mrepo.model.online.ModulesJson

@Entity(
    tableName = "repo",
    primaryKeys = ["url"]
)
data class RepoEntity(
    val url: String,
    val disable: Boolean,
    val size: Int,
    val name: String,
    @Embedded val metadata: Metadata
) {
    constructor(url: String) : this(
        url = url,
        disable = false,
        size = 0,
        name = url,
        metadata = Metadata()
    )

    fun copy(modulesJson: ModulesJson) = copy(
        size = modulesJson.modules.size,
        name = modulesJson.name,
        metadata = Metadata(modulesJson.metadata)
    )

    data class Metadata(
        val homepage: String = "",
        val donate: String = "",
        val support: String = "",
        val timestamp: Double = 0.0,
    ) {
        constructor(original: ModulesJson.Metadata) : this(
            homepage = original.homepage,
            donate = original.donate,
            support = original.support,
            timestamp = original.timestamp
        )
    }
}

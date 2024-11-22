package com.macrosystems.core.data.model

import com.google.gson.annotations.SerializedName
import com.macrosystems.core.domain.Owner
import com.macrosystems.core.domain.RepoData

data class RepoDataModel(
    val id: Int,
    val name: String?,
    @SerializedName("url") val repoUrl: String?,
    @SerializedName("description") val description: String?,
    val owner: OwnerModel?,
    @SerializedName("stargazers_count") val stars: Int?,
    val language: String?
)

fun RepoDataModel.toDomain(): RepoData {
    return RepoData(
        id = this.id,
        name = this.name.orEmpty(),
        repoUrl = this.repoUrl.orEmpty(),
        repoDescription = this.description.orEmpty(),
        owner = Owner(
            id = this.owner?.id ?: 0,
            login = this.owner?.login.orEmpty(),
            profileImageUrl = this.owner?.profilePictureUrl.orEmpty(),
            url = this.owner?.url.orEmpty()
        ),
        stars = this.stars ?: 0,
        language = this.language.orEmpty()
    )
}

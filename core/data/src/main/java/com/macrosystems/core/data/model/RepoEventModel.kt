package com.macrosystems.core.data.model

import com.macrosystems.core.domain.RepoEvent

data class RepoEventModel(
    val type: String,
    val actor: ActorModel,

    )

fun RepoEventModel.toDomain(): RepoEvent {
    return RepoEvent(
        type = this.type,
        actorDisplayLogin = this.actor.displayLogin,
        actorUrl = this.actor.url
    )
}

package org.example.model

import com.fasterxml.jackson.annotation.JsonProperty

data class EventDto(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("event_id")
    val eventId: String? = null,
    @JsonProperty("v")
    val v: String? = null,
    @JsonProperty("group_id")
    val groupId: String? = null,
    @JsonProperty("object")
    val obj: Map<String, *>? = null
)
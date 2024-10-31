package model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MessageForEchoDto(
    @JsonProperty("from_id")
    val fromId: Long,
    @JsonProperty("text")
    val text: String
)
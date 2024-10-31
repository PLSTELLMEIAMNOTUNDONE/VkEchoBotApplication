package org.example.clients

import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap

enum class Operation(
    private val method: String,
    private val httpMethod: HttpMethod,
) {
    SEND_MESSAGE("messages.send", HttpMethod.POST),
    GET_CONFIRM_CODE("groups.getCallbackConfirmationCode", HttpMethod.POST);

    fun action(
        params: LinkedMultiValueMap<String, String>,
        basicClient: BasicClient
    ): String {
        return basicClient.exchange(
            method,
            httpMethod,
            params
        )
    }
}
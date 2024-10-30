package org.example.clients

import clients.VkBasicClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap

@Service
class VkMessageSender(
    private val vkClient: VkBasicClient
) {

    private var method: String = "messages.send"
    private final val log: Logger = LoggerFactory.getLogger(VkMessageSender::class.java)

    fun sendMessage(
        userId: Long,
        message: String
    ): String {
        log.info("sending msg : $message to user : $userId")
        val params = LinkedMultiValueMap<String, String>()
        params["user_id"] = userId.toString()
        params["message"] = message
        params["random_id"] = "0"
        return vkClient.exchange(
            method,
            HttpMethod.POST,
            params
        )
    }
}
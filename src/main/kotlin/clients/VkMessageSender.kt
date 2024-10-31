package clients

import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap

@Component
class VkMessageSender(
    private val vkBasicClient: VkBasicClient
) {
    private val operation = Operation.SEND_MESSAGE

    fun sendMessage(userId: Long, message: String): String {
        val params = LinkedMultiValueMap<String, String>()
        params["user_id"] = userId.toString()
        params["message"] = message
        params["random_id"] = "0"
        return operation.action(
            params,
            vkBasicClient
        )
    }
}
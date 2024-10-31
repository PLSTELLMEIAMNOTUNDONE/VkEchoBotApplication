package clients

import org.example.clients.VkBasicClient
import org.example.clients.VkMessageSender
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpMethod
import org.springframework.util.LinkedMultiValueMap

class VkMessageSenderTest {

    private val vkBasicClient = Mockito.mock(VkBasicClient::class.java)
    private val vkMessageSender = VkMessageSender(vkBasicClient)

    @Test
    fun shouldInvokeExchangeTest() {
        val userId = 0L
        val msg = "msg"
        val params = LinkedMultiValueMap<String, String>()
        params["user_id"] = userId.toString()
        params["message"] = msg
        params["random_id"] = "0"
        Mockito.`when`(vkBasicClient.exchange("messages.send", HttpMethod.POST, params))
            .thenReturn("ok")
        vkMessageSender.sendMessage(userId, msg)
        Mockito.verify(vkBasicClient)
            .exchange("messages.send", HttpMethod.POST, params)
    }
}
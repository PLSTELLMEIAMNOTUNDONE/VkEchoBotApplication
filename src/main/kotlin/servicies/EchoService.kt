package org.example.servicies

import org.example.clients.VkMessageSender
import org.example.model.MessageForEchoDto
import org.springframework.stereotype.Service

@Service
class EchoService(
    private val vkMessageSender: VkMessageSender,
) {
    private fun echo(
        userId: Long,
        prefix: String,
        text: String
    ): String {

        return vkMessageSender.sendMessage(
            userId = userId,
            message = prefix + text
        )
    }

    fun echo(
        messageForEchoDto: MessageForEchoDto
    ): String {
        return echo(
            messageForEchoDto.fromId,
            "Вы отправили",
            messageForEchoDto.text
        )
    }
}
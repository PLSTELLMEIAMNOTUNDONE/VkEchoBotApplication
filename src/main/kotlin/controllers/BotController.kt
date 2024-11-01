package controllers

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import model.EventDto
import model.MessageForEchoDto
import servicies.ConfirmationService
import servicies.EchoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class BotController(
    private val echoService: EchoService,
    private val objectMapper: ObjectMapper,
    private val confirmationService: ConfirmationService
) {

    private final val log: Logger = LoggerFactory.getLogger(BotController::class.java)

    @PostMapping("/")
    fun processEvent(
        @RequestBody dto: EventDto
    ): String {
        log.info("processing : $dto")
        if (dto.type == "confirmation")
            return confirmationService.getConfirmationCode(dto.groupId?.toLong())
        else if (dto.type == "message_new") {
            log.info("prepare to send echo message for dto: $dto")
            val msg = dto.obj?.get("message")
            val messageForEchoDto = objectMapper
                .convertValue(msg, MessageForEchoDto::class.java)
            return echoService.echo(messageForEchoDto)
        }
        return ""
    }
}
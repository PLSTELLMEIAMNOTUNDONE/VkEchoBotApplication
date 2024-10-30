package org.example.controllers

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import lombok.extern.slf4j.Slf4j
import org.example.model.EventDto
import org.example.model.MessageForEchoDto
import org.example.servicies.EchoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class BotController(
    private val echoService: EchoService,
    private val objectMapper: ObjectMapper
) {

    private final val log: Logger = LoggerFactory.getLogger(BotController::class.java)

    @PostMapping("/")
    fun processEvent(
        @RequestBody dto: EventDto
    ): String {
        log.info("processing : $dto")
        if (dto.type == "confirmation")
            return "db4fac29"
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
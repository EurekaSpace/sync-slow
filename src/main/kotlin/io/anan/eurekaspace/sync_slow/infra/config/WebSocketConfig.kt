package io.anan.eurekaspace.sync_slow.infra.config

import io.anan.eurekaspace.sync_slow.adapter.websocket.handler.ChatWebSocketHandler
import io.anan.eurekaspace.sync_slow.adapter.websocket.handler.ChatWebSocketHandlerWithPersist
import io.anan.eurekaspace.sync_slow.adapter.websocket.handler.ChatWebSocketHandlerWithPersistAndKafka
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
import org.springframework.web.socket.adapter.standard.StandardWebSocketHandlerAdapter
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


@Configuration
@EnableWebSocket
class WebSocketConfig(
        private val chatWebSocketHandler: ChatWebSocketHandler,
        private val chatWebSocketHandlerWithPersist: ChatWebSocketHandlerWithPersist,
        private val chatWebSocketHandlerWithPersistAndKafka: ChatWebSocketHandlerWithPersistAndKafka
) : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
                .addHandler(chatWebSocketHandler, "/chat/{roomId}")
                .addHandler(chatWebSocketHandlerWithPersist, "/persist/chat/{roomId}")
                .addHandler(chatWebSocketHandlerWithPersistAndKafka, "/kafka/chat/{roomId}")
                .setAllowedOrigins("*") // CORS 허용
    }
}
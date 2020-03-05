package cn.Ideal.demo.listener;

import cn.Ideal.demo.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebSocketEventListener {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	@Autowired
	private SimpMessageSendingOperations messageSendingOperations;
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event){
		logger.info("Received a new web socket connection");
	}
	// 处理WebSocket断开连接侦听器
	@EventListener
	public void handleWebSocketDisconnectListener(SessionConnectedEvent event){
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		if (headerAccessor.getSessionAttributes() == null) return;
		String username = (String)headerAccessor.getSessionAttributes().get("username");
		if (username != null){
			logger.info("User Disconnected:" + username);
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(ChatMessage.MessageType.LEAVE);
			chatMessage.setSender( username);
			messageSendingOperations.convertAndSend("/topic/public",chatMessage);
		}
	}
}

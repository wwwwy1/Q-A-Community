package cn.Ideal.demo.controller;

import cn.Ideal.demo.entity.Chat;
import cn.Ideal.demo.entity.ChatMessage;
import cn.Ideal.demo.entity.User;
import cn.Ideal.demo.service.IChatService;
import cn.Ideal.demo.service.IUserService;
import cn.Ideal.demo.util.Result;
import cn.Ideal.demo.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {
	@Autowired
	private SimpMessagingTemplate template;
	@Autowired
	private IUserService iUserService;
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private IChatService iChatService;

	private final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	// 以app开头的客户端发送的所有消息都将路由到@MessageMapping的消息处理方法
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
		return chatMessage;
	}
	@MessageMapping("/chat.addUser")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
		headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
		template.convertAndSendToUser(chatMessage.getSender(), "/chat", chatMessage);
		return chatMessage;
	}
	@MessageMapping("/chat.privateMsg")
	public ChatMessage privateMsg(ChatMessage chatMessage){
		LocalDateTime time=LocalDateTime.now();
		String strDate2 = dtf2.format(time);
		chatMessage.setDate(strDate2);
		// 将消息插入数据库
		Chat chat = new Chat(chatMessage.getSender(),chatMessage.getReceiver(),chatMessage.getChannel(),chatMessage.getContent(),0);
		iChatService.save(chat);
		//System.out.println(list);
		//System.out.println(chatMessage);
		template.convertAndSendToUser(chatMessage.getChannel(), "/chat", chatMessage);
		return chatMessage;
	}
	@GetMapping(value = "/user/chatView")
	public ModelAndView goChatView(ModelAndView mav,HttpServletRequest request,String userName){
		mav.setViewName("/user/chatView");
		String token = (String) request.getSession().getAttribute("tokenFront");
		if (StringUtil.isNullOrSpace(token)) mav.setViewName("/user/login");
		String userId = redisTemplate.opsForValue().get(token);
		if (StringUtil.isNullOrSpace(userId)) mav.setViewName("/user/login");
		User byId = iUserService.getById(userId);
		mav.getModel().put("fromUser",byId);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_name",userName);
		User one = iUserService.getOne(queryWrapper);
		mav.getModel().put("toUser",one);
		String ch[] = new String[]{userName,byId.getUserName()};
		Arrays.sort(ch);
		String channel = ch[0] + ch[1];
		mav.getModel().put("channel",channel);
		// 聊天记录
		QueryWrapper<Chat> chatChannel = new QueryWrapper<>();
		chatChannel.eq("channel_id",channel);
		List<Chat> chatList = iChatService.list(chatChannel);
		mav.getModel().put("chatList",chatList);
		// 用户列表展示
		QueryWrapper<Chat> chatQueryWrapper = new QueryWrapper<>();
		chatQueryWrapper.eq("from_user_name",byId.getUserName()).or().eq("to_user_name",byId.getUserName());
		List<Chat> list = iChatService.list(chatQueryWrapper);
		Set<User> chats = new HashSet<>();
		for (Chat chat : list) {
			QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
			if (!byId.getUserName().equals(chat.getFromUserName())){
				userQueryWrapper.eq("user_name",chat.getToUserName());
			}else {
				userQueryWrapper.eq("user_name",chat.getFromUserName());
			}
			User one1 = iUserService.getOne(queryWrapper);
			chats.add(one1);
		}
		mav.getModel().put("chats",chats);
		// 标记已读
		iChatService.alreadyRead(byId.getUserName(),one.getUserName());
		return mav;
	}
}

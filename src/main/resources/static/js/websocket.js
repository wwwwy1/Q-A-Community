'use strict';

var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var imgUrl = document.querySelector('#imgUrl').value;
var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
$(function () {
    connect();
});
function connect() {
    username = document.querySelector('#name').value.trim();
    if(username) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    }
    //event.preventDefault();
}
function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/private/'+username+'/chat', onMessageReceived);

    // Tell your username to the server
    /*stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )*/

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    console.log(imgUrl);
    debugger;
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            receiver:username,
            imgUrl:imgUrl
        };

        stompClient.send("/app/chat.privateMsg", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = "";

    if(message.sender === username) {
        messageElement="<div class=\"chat-message2\">\n" +
        "                                        <img class=\"message-avatar\" src=\"/images/"+ message.imgUrl +"\" alt=\"\">\n" +
        "                                        <div class=\"message\">\n" +
        "                                            <a class=\"message-author\" href=\"#\">"+ message.sender+"</a>\n" +
        "                                            <span class=\"message-date\">  "+ message.date+" </span>\n" +
        "                                            <span class=\"message-content\">\n" + message.content +
        "                                            </span>\n" +
        "                                        </div>\n" +
        "                                    </div>";
    }else {
        messageElement="<div class=\"chat-message1\">\n" +
            "                                        <img class=\"message-avatar\" src=\"/images/"+ message.imgUrl +"\" alt=\"\">\n" +
            "                                        <div class=\"message\">\n" +
            "                                            <a class=\"message-author\" href=\"#\">"+ message.sender+"</a>\n" +
            "                                            <span class=\"message-date\">  "+ message.date+" </span>\n" +
            "                                            <span class=\"message-content\">\n" + message.content +
            "                                            </span>\n" +
            "                                        </div>\n" +
            "                                    </div>";
    }

    messageArea.innerHTML = messageArea.innerHTML + messageElement;
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

messageForm.addEventListener('submit', sendMessage, true)

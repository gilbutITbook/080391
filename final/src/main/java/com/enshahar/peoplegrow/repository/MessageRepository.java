package com.enshahar.peoplegrow.message;

interface MessageRepository {
    List<Message> getMessagesToBeSent();
}
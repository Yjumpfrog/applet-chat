package com.chat.message.service;

public interface ALSendSmsService {
    boolean SendCode(String phone, String code);
}

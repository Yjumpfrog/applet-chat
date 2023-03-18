package com.chat.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chat.message.dao.MessageDao;
import com.chat.message.dao.UserDao;
import com.chat.message.model.BusinessMessageEntity;
import com.chat.message.model.User;
import com.chat.message.service.MessageService;
import com.chat.message.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageDao, BusinessMessageEntity> implements MessageService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sendMessage(BusinessMessageEntity businessMessageEntity) {
        //将此二人以往的最后聊天消息，置为不是最后的消息

        UpdateWrapper<BusinessMessageEntity> updateWrapper = new UpdateWrapper();
        updateWrapper.set("is_last",0);
        updateWrapper.eq("del_flag", 0);
        updateWrapper.and(wrapper -> wrapper.and(item -> item.eq("sender",businessMessageEntity.getSender()).eq("receiver",businessMessageEntity.getReceiver()))
                .or(item -> item.eq("receiver",businessMessageEntity.getSender()).eq("sender",businessMessageEntity.getReceiver()))
        );
        this.update(updateWrapper);
        // 保存消息
        businessMessageEntity.setIsLast(true);
        businessMessageEntity.setDelFlag("0");
        this.save(businessMessageEntity);
        return 1;
    }
}

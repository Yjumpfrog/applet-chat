package com.chat.message.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chat.message.model.BusinessMessageEntity;
import com.chat.message.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao extends BaseMapper<BusinessMessageEntity> {
}

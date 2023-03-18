package com.chat.message.web;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chat.message.model.BusinessMessageEntity;
import com.chat.message.service.MessageService;
import com.chat.message.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/mobile/message")
public class MobileMessageController {

    @Autowired
    private MessageService messageService;


    /**
     * 分页获取我与某人的历史聊天记录
     * @param myMemberId
     * @param youMemberId
     * @return
     */
    @GetMapping("/getMessageHistory")
    public R getMessageHistory(Long myMemberId, Long youMemberId, int pageNo, int pageSize){
        Page page = new Page<>(pageNo,pageSize);
        //查询历史聊天记录
        QueryWrapper<BusinessMessageEntity> queryWrapper = new QueryWrapper();
        queryWrapper.eq("del_flag", 0);
        queryWrapper.and(wrapper -> wrapper.and(item -> item.eq("sender",myMemberId).eq("receiver",youMemberId))
        .or(item -> item.eq("receiver",myMemberId).eq("sender",youMemberId))
        );
        queryWrapper.orderByDesc("created_time");
        Page pageData = messageService.page(page, queryWrapper);
        //第一次获取，将此人与对方的聊天消息，全部置为已读
        if(pageNo==1){
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("del_flag", 0);
            updateWrapper.eq("sender",youMemberId);
            updateWrapper.eq("receiver",myMemberId);
            updateWrapper.set("is_read",1);
            messageService.update(updateWrapper);
        }
        return R.ok().put("datas",pageData);
    }
}

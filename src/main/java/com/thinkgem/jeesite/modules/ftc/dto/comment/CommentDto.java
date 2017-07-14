package com.thinkgem.jeesite.modules.ftc.dto.comment;

import com.thinkgem.jeesite.common.rest.BaseDto;
import com.thinkgem.jeesite.modules.ftc.dto.customer.CustomerDto;

import java.util.List;

/**
 * Created by bingbing on 2017/6/21.
 */
public class CommentDto extends BaseDto<CommentDto> {
    private String id;//id
    private String content;//内容
    private String type;//类型
    private CustomerDto user;//客户


    private List<ReplyDto> replyList;//回复列表
     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomerDto getUser() {
        return user;
    }

    public void setUser(CustomerDto user) {
        this.user = user;
    }

    public List<ReplyDto> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyDto> replyList) {
        this.replyList = replyList;
    }
}

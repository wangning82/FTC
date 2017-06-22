package com.thinkgem.jeesite.modules.ftc.dto.comment;

import com.thinkgem.jeesite.common.rest.BaseDto;

import java.util.List;

/**
 * Created by bingbing on 2017/6/21.
 */
public class CommentDto extends BaseDto<CommentDto> {
    private List<ReplyDto> replyList;

    public List<ReplyDto> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyDto> replyList) {
        this.replyList = replyList;
    }
}

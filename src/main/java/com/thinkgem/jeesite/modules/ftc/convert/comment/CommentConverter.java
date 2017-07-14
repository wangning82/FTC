package com.thinkgem.jeesite.modules.ftc.convert.comment;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.dto.comment.CommentDto;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Comment;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangqingxiang on 2017/6/21.
 */
@Component
public class CommentConverter extends BaseConverter<Comment,CommentDto> {

    @Override
    public Comment convertDtoToModel(CommentDto dto) {
        Comment comment=new Comment();
        comment.setId(dto.getId());
        comment.setType(dto.getType());
        comment.setCustomer(new Customer(dto.getUser().getId()));
        comment.setContent(dto.getContent());
        return comment;
    }
}

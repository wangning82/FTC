package com.thinkgem.jeesite.modules.ftc.convert.comment;

import com.thinkgem.jeesite.common.rest.BaseConverter;
import com.thinkgem.jeesite.modules.ftc.convert.customer.CustomerConverter;
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

    @Autowired
    private CustomerConverter customerConverter;
    @Override
    public Comment convertDtoToModel(CommentDto dto) {
        Comment comment=new Comment();
        comment.setId(dto.getId());
        comment.setType(dto.getType());
        comment.setCustomer(new Customer(dto.getUser().getId()));
        comment.setContent(dto.getContent());
        return comment;
    }

    @Override
    public CommentDto convertModelToDto(Comment model) {
        CommentDto dto=new CommentDto();
        dto.setId(model.getId());
        dto.setContent(model.getContent());
        dto.setType(model.getType());
        dto.setUser(customerConverter.convertModelToDto(model.getCustomer()));
        return dto;
    }
}

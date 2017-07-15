package com.thinkgem.jeesite.modules.ftc.rest.comment;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.rest.BaseRestController;
import com.thinkgem.jeesite.common.rest.RestResult;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ftc.convert.comment.CommentConverter;
import com.thinkgem.jeesite.modules.ftc.convert.comment.ReplyConverter;
import com.thinkgem.jeesite.modules.ftc.dto.advert.AdvertDetailDto;
import com.thinkgem.jeesite.modules.ftc.dto.comment.CommentDto;
import com.thinkgem.jeesite.modules.ftc.dto.comment.ReplyDto;
import com.thinkgem.jeesite.modules.ftc.entity.advert.Advert;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Comment;
import com.thinkgem.jeesite.modules.ftc.entity.comment.Reply;
import com.thinkgem.jeesite.modules.ftc.entity.customer.Customer;
import com.thinkgem.jeesite.modules.ftc.entity.product.Product;
import com.thinkgem.jeesite.modules.ftc.service.comment.CommentService;
import com.thinkgem.jeesite.modules.ftc.service.comment.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.ehcache.util.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangqingxiang on 2017/6/22.
 */
@RestController
@RequestMapping(value = "/rest/ftc/comment/")
@Api(value = "客户评价", description = "评价管理")
public class CommentRestController extends BaseRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyConverter replyConverter;

    /**
     * 商品评价
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"list"}, method = {RequestMethod.GET})
    @ApiOperation(value = "商品的评价", notes = "根据商品id获取评价列表")
    public RestResult list(@RequestParam("productId") String productId) {
        if(StringUtils.isEmpty(productId)){
            return new RestResult(CODE_ERROR,"商品id不能为空",null);
        }
        Comment comment = new Comment();
        comment.setProduct(new Product(productId));
        List<Comment> commentList = commentService.findList(comment);
        List<CommentDto> commentDtoList = commentConverter.convertListFromModelToDto(commentList);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, commentDtoList);
    }


    /**
     * 评价详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"info"}, method = {RequestMethod.GET})
    @ApiOperation(value = "评价的详情", notes = "获取评论详情及回复列表")
    public RestResult info(CommentDto dto) {
        Comment comment = commentConverter.convertDtoToModel(dto);
        if(StringUtils.isEmpty(comment.getId())){
            return new RestResult(CODE_ERROR,"id不能为空",null);
        }
        comment = commentService.get(comment.getId());
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, commentConverter.convertModelToDto(comment));
    }

    /**
     * 评价详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"save"}, method = {RequestMethod.POST})
    @ApiOperation(value = "回复或点赞", notes = "回复或点赞")
    public RestResult save(@RequestParam("token") String token,CommentDto dto) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Comment comment = commentConverter.convertDtoToModel(dto);
            if(StringUtils.isEmpty(comment.getType())){
                return new RestResult(CODE_ERROR,"类型不能为空",null);
            }
            commentService.save(comment);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        }

    }

    /**
     * 评价详情
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"delete"}, method = {RequestMethod.POST})
    @ApiOperation(value = "删除评价", notes = "删除评价")
    public RestResult delete(@RequestParam("token") String token,@RequestParam("cid") String id) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Comment comment = new Comment(id);

            commentService.delete(comment);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        }

    }
    /**
     * 取消点赞
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"cancelPraise"}, method = {RequestMethod.POST})
    @ApiOperation(value = "取消点赞", notes = "取消")
    public RestResult cancelPraise(@RequestParam("token") String token,@RequestParam("gid")String id) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Comment comment=new Comment();
            comment.setCustomer(customer);
            comment.setProduct(new Product(id));
            comment.setType("1");
            if(StringUtils.isEmpty(comment.getType())){
                return new RestResult(CODE_ERROR,"类型不能为空",null);
            }

            List<Comment> comments=commentService.findList(comment);
            if(comments==null||comments.size()==0){
                return new RestResult(CODE_SUCCESS, "没有点赞");
            }
            commentService.delete(comments.get(0));
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        }

    }
    /**
     * 点赞
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"praise"}, method = {RequestMethod.POST})
    @ApiOperation(value = "点赞", notes = "点赞")
    public RestResult praise(@RequestParam("token") String token,@RequestParam("gid")String id) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Comment comment=new Comment();
            comment.setCustomer(customer);
            comment.setProduct(new Product(id));
            comment.setType("1");
            if(StringUtils.isEmpty(comment.getType())){
                return new RestResult(CODE_ERROR,"类型不能为空",null);
            }
            List<Comment> comments=commentService.findList(comment);
            if(comments !=null&&comments.size()>0){
                return new RestResult(CODE_ERROR, "你已经点过赞了");
            }
            commentService.save(comment);
            return new RestResult(CODE_SUCCESS, MSG_SUCCESS, null);
        }

    }


    /**
     * 回复列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"replylist"}, method = {RequestMethod.GET})
    @ApiOperation(value = "评价的回复", notes = "获取评价的回复列表")
    public RestResult replyList(CommentDto dto, HttpServletRequest request, HttpServletResponse response) {
        Comment comment = commentConverter.convertDtoToModel(dto);
        if(StringUtils.isEmpty(comment.getId())){
            return new RestResult(CODE_ERROR,"id不能为空",null);
        }
        Page<Reply> replyPage = replyService.findPage(new Page<Reply>(request, response), new Reply(comment));
        List<ReplyDto> replyDtoList = replyConverter.convertListFromModelToDto(replyPage.getList());
        dto.setReplyList(replyDtoList);
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS, dto);
    }
    /**
     * 回复列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"reply"}, method = {RequestMethod.GET})
    @ApiOperation(value = "回复", notes = "回复评价")
    public RestResult reply(@RequestParam("token") String token,@RequestParam("id")String id,@RequestParam("content")String content) {

        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Reply reply=new Reply();
            reply.setCustomer(customer);
            reply.setComment(new Comment(id));
            reply.setContent(content);
            replyService.save(reply);
        }
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }
    /**
     * 删除回复
     *
     * @param
     * @return
     */
    @RequestMapping(value = {"reply/delete"}, method = {RequestMethod.GET})
    @ApiOperation(value = "删除回复", notes = "删除回复")
    public RestResult reply(@RequestParam("token") String token,@RequestParam("id")String id) {
        Customer customer = findCustomerByToken(token);
        if (customer == null) {
            return new RestResult(CODE_NULL, "令牌无效，请重新登录！");
        } else {
            Reply reply=replyService.get(id);
            if(!reply.getCustomer().getId().equals(customer.getId())){
                return new RestResult(CODE_ERROR, "只能删除自己的回复");
            }

            replyService.delete(reply);
        }
        return new RestResult(CODE_SUCCESS, MSG_SUCCESS);
    }

}

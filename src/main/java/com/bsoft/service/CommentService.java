package com.bsoft.service;

import com.bsoft.dao.CommentRepository;
import com.bsoft.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个评论
     * @param comment
     */
    public void saveComment(Comment comment){
        commentRepository.save(comment);
       // mongoTemplate.save(comment);
       // mongoTemplate.insert(comment);
    }

    /**
     * 批量保存评论
     * @param <
     */
    public void mutilSaveComment(List<Comment> list){
        commentRepository.saveAll(list);
       // mongoTemplate.insertAll(list);
    }

    /**
     * 更新一个评论
     * @param comment
     */
    public void updateComment(Comment comment){
         commentRepository.save(comment);
    }

    /**
     * 查询全部评论
     * @return
     */
    public List<Comment> findCommentAll(){
       // return  commentRepository.findAll();
        return mongoTemplate.findAll(Comment.class);
    }

    /**
     * 条件查询
     * @return
     */
    public List<Comment> findCommentByContion(Query query){
        return  mongoTemplate.find(query,Comment.class);
    }

    /**
     * 查询全部评论通过id排序
     * @return
     */
    public List<Comment> findCommentAllOrder(){
      //  return  commentRepository.findAll(Sort.by(Sort.Order.desc("_id")));


        Query query=new Query();
       query.with(Sort.by(Sort.Direction.DESC,"id"));
        return mongoTemplate.find(query,Comment.class);
    }


    /**
     * 通过id查询评论
     * @return
     */
    public Comment findCommentById(String id){
        //return  commentRepository.findById(id).get();
        return mongoTemplate.findById(id,Comment.class);
    }

    /**
     * 通过父id分页查询
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findByparentIdPage1(String parentId, int page,int size){
        return  commentRepository.findByParentId(parentId, PageRequest.of(page-1,size));
    }

    public List<Comment> findByparentIdPage2(String parentId, int page,int size){
        Query query=Query.query(Criteria.where("parentId").is(parentId));
        query.with(PageRequest.of(page-1,size));
        return  mongoTemplate.find(query,Comment.class);
    }

    /**
     * 通过id删除
     * @param id
     */
    public void deleteById(String id){
      //  commentRepository.deleteById(id);
        Comment comment=new Comment();
        comment.setId(id);
        mongoTemplate.remove(comment);
    }

    /**
     * 删除全部数据
     * @param
     */
    public void deleteAll(){
        commentRepository.deleteAll();
    }

    /**
     * 批量删除
     * @param
     */
    public void deleteMulti(List<Comment> list){
        commentRepository.deleteAll(list);
    }

    /**
     * 点赞数加一
     * @param id
     */
    public void updateCommentLikeNumm(String id){
        //点赞数加一    效率低，增加id开销
     //   Comment comment=commentRepository.findById(id).get();
      //  comment.setLikeNum(comment.getLikeNum()+1);
      //  commentRepository.save(comment);

       //查询对象
        Query query=Query.query(Criteria.where("_id").is(id));
        //更新对象
        Update update=new Update();
        //局部更新 相当于$set
       // update.set(key,value);
        //递增$inc
        //update.inc("likeNum",1);
        update.inc("likeNum");
        //查询对象   更新对象   集合的名称或实体类的类型Comment.class
        mongoTemplate.updateFirst(query,update,"comment");
    }

    /**
     * 统计
     * @return
     */
    public Long commentCount(Query query){
        return mongoTemplate.count(query,Comment.class);
    }
}

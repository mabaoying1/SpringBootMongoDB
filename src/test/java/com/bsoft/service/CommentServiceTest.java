package com.bsoft.service;

import com.bsoft.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

	@Autowired
	private CommentService commentService;

	/**
	 * 新增单个评论
	 */
	@Test
	public void saveCommentTest(){
		Comment comment=new Comment();
		//comment.setId("2");
		comment.setArticleId("777");
		comment.setContent("添加数据测试");
		comment.setPublishTime(new Date());
		comment.setUserId("1001");
		comment.setNickName("张三");
		comment.setCreateDateTime(new Date());
		comment.setLikeNum(1);
		comment.setReplyNum(0);
		comment.setState("1");
		comment.setParentId("0");
		commentService.saveComment(comment);
	}

	/**
	 * 批量新增
	 */
	@Test
	public void mutilSaveComment(){
		List<Comment> list=new ArrayList<>();
		Comment comment;
		for(int i=1;i<=10;i++){
			comment=new Comment();
			comment.setId(""+i);
			comment.setArticleId(""+i);
			comment.setContent("添加数据测试"+i);
			comment.setPublishTime(new Date());
			comment.setUserId("1001");
			comment.setNickName("张三");
			comment.setCreateDateTime(new Date());
			comment.setLikeNum(0);
			comment.setReplyNum(0);
			comment.setState("1");
			comment.setParentId("0");
			list.add(comment);
		}
		commentService.mutilSaveComment(list);
	}

	/**
	 * 查询全部
	 */
	@Test
	public void findCommentListTest() {
		List<Comment> list=commentService.findCommentAll();
		for(Comment comment:list){
			System.out.println(comment);
		}
	}

	/**
	 * 通过id排序
	 */
	@Test
	public void findCommentListOrderTest() {
		List<Comment> list=commentService.findCommentAllOrder();
		for(Comment comment:list){
			System.out.println(comment);
		}
	}

	/**
	 * 通过id删除
	 */
	@Test
	public void findCommentById() {
		Comment comment=commentService.findCommentById("1");
		System.out.println(comment);
	}

	/**
	 * 通过父id分页查询1
	 */
	@Test
	public void findByParentId(){
		Page<Comment> page=commentService.findByparentIdPage1("0",1,10);
		System.out.println(page.getTotalElements());
		System.out.println(page.getContent());
	}

	/**
	 * 通过父id分页查询2
	 */
	@Test
	public void findByparentIdPage2(){
		List<Comment> list=commentService.findByparentIdPage2("0",1,10);
		for(Comment comment1:list){
			System.out.println(comment1);
		}
	}

	/**
	 * 通过id删除评论
	 */
	@Test
	public void deleteById(){
		commentService.deleteById("1");
	}

	/**
	 * 删除全部
	 */
	@Test
	public void deleteAll(){
		commentService.deleteAll();
	}

	/**
	 * 批量删除
	 */
	@Test
	public void deleteMulti(){
		List<Comment> list=new ArrayList<>();
		Comment comment;
		for(int i=1;i<=10;i++) {
			comment = new Comment();
			comment.setId("" + i);
			list.add(comment);
		}
		commentService.deleteMulti(list);
	}

	/**
	 * 多条件查询in
	 */
	@Test
	public void findCommentByContion(){
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		//查询对象
		Query query=Query.query(Criteria.where("_id").in(list));

		List<Comment> list2=commentService.findCommentByContion(query);
		for(Comment comment1:list2){
			System.out.println(comment1);
		}
	}

	/**
	 * 多条件查询大于小于等于
	 */
	@Test
	public void findCommentContionByGtLt(){
		//查询对象
		Query query=Query.query(Criteria.where("likeNum").gte(2).lte(6));
		List<Comment> list =commentService.findCommentByContion(query);
		for(Comment comment1:list){
			System.out.println(comment1);
		}
	}

	/**
	 * 多条件查询and
	 */
	@Test
	public void findCommentContionByAnd(){
		//查询对象
		Query query=Query.query(new Criteria().andOperator(Criteria.where("likeNum").gte(2),Criteria.where("state").is("1")));
		List<Comment> list =commentService.findCommentByContion(query);
		for(Comment comment1:list){
			System.out.println(comment1);
		}
	}

	/**
	 * 多条件查询or
	 */
	@Test
	public void findCommentContionByOr(){
		//查询对象
		Query query=Query.query(new Criteria().orOperator(Criteria.where("likeNum").gte(2),Criteria.where("state").is("0")));
		List<Comment> list =commentService.findCommentByContion(query);
		for(Comment comment1:list){
			System.out.println(comment1);
		}
	}

	/**
	 * 更新 点赞数加一
	 */
	@Test
	public void updateCommentLikeNumm(){
		commentService.updateCommentLikeNumm("1");
	}

	/**
	 * 统计查询
	 */
	@Test
	public void commentCount(){
		Query query=Query.query(Criteria.where("likeNum").gte(2));
		Query query1=new Query();
		Long count1=commentService.commentCount(query);
		Long count2=commentService.commentCount(query1);
		System.out.println("点赞数大于等于2的文档有======="+count1);
		System.out.println("统计总数======="+count2);
	}
}

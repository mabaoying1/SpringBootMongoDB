package com.bsoft.dao;

import com.bsoft.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {


    Page<Comment> findByParentId(String parentId,Pageable pageable);


}

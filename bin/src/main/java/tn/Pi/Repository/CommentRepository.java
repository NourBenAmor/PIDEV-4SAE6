package com.skander.forum.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skander.forum.model.Comment;





public interface CommentRepository extends JpaRepository<Comment, Long> {

}

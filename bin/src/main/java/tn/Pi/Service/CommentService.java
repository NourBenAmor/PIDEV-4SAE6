package com.skander.forum.service;

import java.util.List;

import com.skander.forum.model.Comment;





public interface CommentService {

	Comment saveComment(Comment c);
	Comment updateComment(Comment c);
	void deleteComment(Comment c);
	void deleteCommentById(Long id);
	Comment getComment(Long id);
	List<Comment> getAllComments();
}

package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Comment;







public interface CommentService {

	Comment saveComment(Comment c);
	Comment updateComment(Comment c);
	void deleteComment(Comment c);
	void deleteCommentById(Long id);
	Comment getComment(Long id);
	List<Comment> getAllComments();
}

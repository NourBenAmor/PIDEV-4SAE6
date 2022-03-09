package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Comment;







public interface CommentRepository extends JpaRepository<Comment, Long> {

}

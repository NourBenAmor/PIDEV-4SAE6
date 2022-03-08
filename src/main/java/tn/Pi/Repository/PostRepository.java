package tn.Pi.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Post;
import tn.Pi.entities.User;



public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User u);
	List<Post> findByDescriptionContaining(String text);

}

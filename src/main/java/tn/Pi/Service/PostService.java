package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Post;
import tn.Pi.entities.User;



public interface PostService {

	Post savePost(Post p);
	Post updatePost(Post p);
	void deletePost(Post p);
	void deletePostById(Long id);
	Post getPost(Long id);
	List<Post> getAllPosts();
	String testStars(float stars);
	List<Post> getPostsByUser(User u);
	List<Post> search(String text);
}

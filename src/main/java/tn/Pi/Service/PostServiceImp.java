package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.PostRepository;
import tn.Pi.entities.Post;
import tn.Pi.entities.User;




@Service
public class PostServiceImp implements PostService {

	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post savePost(Post p) {
		return postRepository.save(p);
	}

	@Override
	public Post updatePost(Post p) {
		return postRepository.save(p);
	}

	@Override
	public void deletePost(Post p) {
		postRepository.delete(p);
		
	}

	@Override
	public void deletePostById(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public Post getPost(Long id) {
		return postRepository.findById(id).get();
	}

	@Override
	public List<Post> getPostsByUser(User u) {
		return postRepository.findByUser(u);
	}
	
	@Override
	public List<Post> getAllPosts() {
		
		return postRepository.findAll();
	}
	
	@Override
	public String testStars(float stars)
	{
		String msg ;
		String tag;
		if (stars>3)
		{
			msg = "\nCongratulations! ";
			tag="reached";
		}
		else
		{
			msg="\nSadly..";
			tag="only";
		}
		return msg+"\nThis post has "+tag+" "+Math.round(stars)+" stars ";
	}
	
	@Override
	public List<Post> search(String text)
	{
		return postRepository.findByDescriptionContaining(text);
	}
	
	@Override 
	public Post findById(Long id)
	{
		return postRepository.findById(id).orElse(null);
	}
	
}

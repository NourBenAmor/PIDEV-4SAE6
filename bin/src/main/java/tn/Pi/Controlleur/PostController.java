package com.skander.forum.controllers;


import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skander.forum.model.Ban;
import com.skander.forum.model.Historique;
import com.skander.forum.model.Post;
import com.skander.forum.model.User;
import com.skander.forum.repos.BanRepository;
import com.skander.forum.repos.UserRepository;
import com.skander.forum.service.BadWordsFilter;
import com.skander.forum.service.BanService;
import com.skander.forum.service.FileUploadService;
import com.skander.forum.service.HistoriqueService;
import com.skander.forum.service.PostService;
import com.skander.forum.service.RateService;


@RestController
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	FileUploadService fileupload;
	@Autowired
	RateService rateservice;
	@Autowired
	BanService banservice;
	@Autowired
	HistoriqueService historiqueService;
	@Autowired
	UserRepository userRepos;
	@RequestMapping("/showCreate")
	public String showCreate()
	{
		return "createPost";
	}
	
	@PostMapping(value="/savePost")
	public String savePost(@RequestParam("description") String description,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException  
	{
		Post post = new Post();
		// Creating the LocalDatetime object
				LocalDate currentLocalDate = LocalDate.now();
				
				// Getting system timezone
				ZoneId systemTimeZone = ZoneId.systemDefault();
				
				// converting LocalDateTime to ZonedDateTime with the system timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
				
				// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
				Date dateCreation = Date.from(zonedDateTime.toInstant());
		User currentuser = userRepos.findById((long)1).orElse(null);
		Ban ban=banservice.getBanByUser(currentuser);
		
		if(ban!=null)
		{
		if(ban.getEndBan().compareTo(dateCreation)<0)
		{
		post.setDescription(description);
		//file upload
		fileupload.uploadfile(file);
		post.setFile(file.getOriginalFilename());
		post.setUser(currentuser);
        post.setDateDeCreation(dateCreation);
		postService.savePost(post);
		return "post created!";
		}
		}
		return "Oops , seems like u are banned from creating a post until " + ban.getEndBan();
	}
	
	
	
	@GetMapping("/ListePosts")
	public List<Post> listePosts()
	{
		List<Post> posts = postService.getAllPosts();	
		return posts;	
	}
	
	@DeleteMapping("/supprimerPost")
	public String supprimerPost(@RequestParam Long id)
	{
		postService.deletePostById(id);
		return "post deleted!";	
	}
	
	@GetMapping("/getPostById")
	public Post editerPost(@RequestParam Long id)
	{
		Post p= postService.getPost(id);
		return p;	
	}

	@PutMapping("/updatePost")
	public String updatePost(@RequestBody Post post) 
	{
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateCreation = Date.from(zonedDateTime.toInstant());
			User currentuser = new User();
			Long id = (long) 1;
			
			currentuser.setIdUser(id);
			post.setUser(currentuser);
			post.setDateDeCreation(dateCreation);
		  postService.updatePost(post);
		 // List<Post> posts = postService.getAllPosts();	
		return "post updated!";
	}
	
	
	@PostMapping(value="/checkPost")
	public String checkPost(@RequestBody Post post)  
	{
		
		// Creating the LocalDatetime object
				LocalDate currentLocalDate = LocalDate.now();
				LocalDate banendlocal = LocalDate.now().plusDays(30);
				// Getting system timezone
				ZoneId systemTimeZone = ZoneId.systemDefault();
				
				// converting LocalDateTime to ZonedDateTime with the system timezone
				ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
				ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
				// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
				Date dateCreation = Date.from(zonedDateTime.toInstant());
				Date dateEndBan = Date.from(zonedDateTimeEnd.toInstant());
		User currentuser = new User();
		Long id = (long) 1;
		String banmsg="";
		currentuser.setIdUser(id);
		post.setUser(currentuser);
        post.setDateDeCreation(dateCreation);
        BadWordsFilter.loadConfigs();
       
		String check=BadWordsFilter.filterText(post.getDescription(), "username","post");
        //postService.savePost(post);
		//check ban if needed
		
		//get the ban status of the current user
		Ban b=banservice.getBanByUser(currentuser);
		
		
		if(check!=post.getDescription())
		{
			if(b!=null)
			{
				
					int penalite= b.getPenalites();
					penalite=penalite+1;
					if(penalite>2)
					{
						b.setDebutBan(dateCreation);
						b.setEndBan(dateEndBan);
						b.setPenalites(0);
						b.setStatus(true);
						banmsg="\nSadly you have been banned from creating a post you can no longer add posts until "+dateEndBan;
					}
					else
					{
						b.setPenalites(penalite);
					}
					banservice.updateBan(b);
			}
			else{
				Ban ban= new Ban();
				ban.setPenalites(1);
				ban.setStatus(false);
				ban.setUser(currentuser);
				banservice.saveBan(ban);
			}
		}
		check=check+banmsg;
        return check;
	}
	
	
	@GetMapping("/PostStatistics")
	public String statistics()
	{
		// Creating the LocalDatetime object
		LocalDate currentLocalDate = LocalDate.now();
		LocalDate banendlocal = LocalDate.now().minusDays(30);
		// Getting system timezone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		ZonedDateTime zonedDateTimeEnd = banendlocal.atStartOfDay(systemTimeZone);
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date dateToday = Date.from(zonedDateTime.toInstant());
		Date dateMounthAgo = Date.from(zonedDateTimeEnd.toInstant());
		
		User u = new User();
		Long idUser=(long) 1;
		u.setIdUser(idUser);
		List<Post> posts  =postService.getPostsByUser(u);
		float stars;
		int totalComments;
		String msg="Your Monthly Review is Here ! \n________________________________________________________\n";
		String starsMsg;
		
		for (int i = 0; i < posts.size(); i++)
		{
			if(posts.get(i).getDateDeCreation().compareTo(dateMounthAgo)>0){
			msg=msg+"Post Id = "+posts.get(i).getIdPost()+"\n"; 
			msg=msg+"Post text : "+posts.get(i).getDescription() +"\n";
			stars=rateservice.Statistiques(posts.get(i));
			totalComments=postService.getPost(posts.get(i).getIdPost()).getComments().size();
			starsMsg=postService.testStars(stars);
			msg=msg+starsMsg+"\nThis Post have "+totalComments+" Comments \n";
			msg=msg+"________________________________________________________\n";
			}
		}
		
		return msg;
	}
	
	@PostMapping("/Search")
	public List<Post> searchPosts (@RequestParam String text)
	{
		User currentUser=new User();
		currentUser.setIdUser((long)1);
		Historique hist = new Historique();
		hist.setUser(currentUser);
		hist.setSearch(text);
		historiqueService.saveHistorique(hist);
		return postService.search(text);
	}
	
	@GetMapping("/recomended")
	public List<Post> recommend()
	{
		User currentUser=new User();
		currentUser.setIdUser((long)1);
		List<Historique> histList = historiqueService.getHistoriqueByUser(currentUser);
		List<Post> recommend = new ArrayList<Post>();
		for (int i = 0; i < histList.size(); i++)
		{
			List<Post> posts = postService.search(histList.get(i).getSearch());
			recommend.addAll(posts);
		}
		Set<Post> mylist = new HashSet<Post>(recommend);
		List<Post> recomm = new ArrayList<Post>(mylist);
		return recomm;
	}
	
	
	
	@GetMapping("/exportPdf")
	public void exportToPDF(HttpServletResponse response){
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filenme=posts.pdf";
		
		response.setHeader(headerKey, headerValue);
		
	}

}


	/*
	@PostMapping("/chatBot")
	public String chat(@RequestParam("reponse") String reponse)
	{
		
		if(reponse.equals("Hi"))
		{
			question="whats your case ?\nKidnaping\nMental HealthCare";
			return question;
		}
		
		if (question.equals("whats your case ?\nKidnaping\nMental HealthCare" ))
		{
			if(reponse.equals("Kidnaping"))
			{
				question="do u want to contact an expert?-the police-";
				return question;
			}
			else if(reponse.equals("Mental HealthCare"))
			{
				question="do u want to contact an expert?-Psychotherapy-";	
				return question;				}
			}	
		
		return "write something!";
		
	}

}
*/
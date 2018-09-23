package com.home.workshopmongo.config;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.home.workshopmongo.domain.Comment;
import com.home.workshopmongo.domain.Post;
import com.home.workshopmongo.domain.User;
import com.home.workshopmongo.repository.PostRepository;
import com.home.workshopmongo.repository.UserRepository;

@Configuration
public class PopulateDatabase implements CommandLineRunner {

	private static Logger LOGGER = LogManager.getLogger(PopulateDatabase.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	private final String MADARA_COMMENT_1 = "I guess neither you, nor I could achieve what we wanted";
	
	private final String HASHIRAMA_COMMENT_1 = "It's never that easy. Out job is to do all we can while we're alive. "
			+ "And then bequeath the rest for future generations to accomplish.";
	
	private final String MADARA_COMMENT_2 = "So naive... as usual. You... were always... the optimist. But perhaps... that is the correct path. "
			+ "My dream... was squashed. But your dream... still lives on.";
	
	private final String HASHIRAMA_COMMENT_2 = "We were both too hasty. We didn't need to fulfill our dreams ourselves. "
			+ "It was more important to cultivate those who whould come after us, to whom we could entrust our dreams.";
	
	private final String MADARA_COMMENT_3 = "Which means... I would've failed anyways... since I've always hated someone... standing behind... me.";
	
	private final String HASHIRAMA_COMMENT_3 = "When we were kids, you once said that we're shinobi and we don't know when we'll die. "
			+ "And that for neither side to die, we'd both have to reveal what's inside of us. And pour each other drinks to toast like brothers. "
			+ "But we're both about to die. Right now, we can drink together, as war buddies.";
	
	private final String MADARA_COMMENT_4 = "War buddies... huh? Well... I guess... that's... ok.. ay...";
	
	private final String HIDENORI_COMMENT_1 = "The wind is troubled today.";
	
	private final String YASSAN_COMMENT_1 = "But, this wind... is ever so softly weepíng.";
	
	private final String HIDENORI_COMMENT_2 = "Those aren't tears of sadness. They're tears of happiness, right?"; 
	
	private final String HIDENORI_COMMENT_3 = "Let us make haste... lest the wind ceases.";
	
	private final String YASSAN_COMMENT_2 = ":3";
	
	@Override
	public void run(String... args) throws Exception {
		
		postRepository.deleteAll();
		LOGGER.info("Deleted posts collection from database");
		
		userRepository.deleteAll();
		LOGGER.info("Deleted users collection from database");
		
		User madara = new User(null, "Uchiha Madara", "madara@uchiha.konoha.co.jp");
		User hashirama = new User(null, "Senjuu Hashirama", "hashirama@senjuu.konoha.co.jp");
		User d = new User(null, "D.", "d@d.co.jp");
		
		User yassan = new User(null, "Yassan - Literary Girl", "yassan@danshikoukouseinonichijou.co.jp");
		User hidenori = new User(null, "Tabata Hidenori", "hidenori@danshikoukouseinonichijou.co.jp");
		
		List<Comment> comments1 = createComment(
			new Comment(madara.getName(), MADARA_COMMENT_1, LocalDateTime.now()),
			new Comment(hashirama.getName(), HASHIRAMA_COMMENT_1, LocalDateTime.now()),
			new Comment(madara.getName(), MADARA_COMMENT_2, LocalDateTime.now()),
			new Comment(hashirama.getName(), HASHIRAMA_COMMENT_2, LocalDateTime.now()),
			new Comment(madara.getName(), MADARA_COMMENT_3, LocalDateTime.now()),
			new Comment(hashirama.getName(), HASHIRAMA_COMMENT_3, LocalDateTime.now()),
			new Comment(madara.getName(), MADARA_COMMENT_4, LocalDateTime.now())
		);
		
		List<Comment> comments2 = createComment(
			new Comment(hidenori.getName(), HIDENORI_COMMENT_1, LocalDateTime.now()),
			new Comment(yassan.getName(), YASSAN_COMMENT_1, LocalDateTime.now()),
			new Comment(hidenori.getName(), HIDENORI_COMMENT_2, LocalDateTime.now()),
			new Comment(hidenori.getName(), HIDENORI_COMMENT_3, LocalDateTime.now()),
			new Comment(yassan.getName(), YASSAN_COMMENT_2, LocalDateTime.now())
		);
		
		Post endOfWar = new Post(null, "War is over", "Finally the war came into an end", LocalDateTime.now(), hashirama);
		addComments(endOfWar, comments1);
		
		Post endOfHighSchool = new Post(null, "Finally graduated!", "Today is not the end but the very beginning.", LocalDateTime.now(), yassan);
		addComments(endOfHighSchool, comments2);
		
		LOGGER.info("Populating users collection with: 'Madara', 'Hashirama' and 'D.'");
		userRepository.saveAll(Arrays.asList(madara, hashirama, d, hidenori, yassan));
		
		LOGGER.info("Populating posts collection with: 'The end of war' and 'The end of highschool years'");
		postRepository.saveAll(Arrays.asList(endOfWar, endOfHighSchool));
		
	}
	
	public static List<Comment> createComment(Comment... comments) {
		List<Comment> c = Arrays.asList(comments);
		return c;
	}
	
	public static void addComments(Post post, List<Comment> comments) {
		comments.forEach(comment -> post.addComment(comment));
	}
	
}

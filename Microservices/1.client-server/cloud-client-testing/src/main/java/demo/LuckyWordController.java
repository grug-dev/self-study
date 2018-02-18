package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuckyWordController {

	@Value("${lucky-word}")
	String luckyWord;
	
	@Autowired
	private Environment enviroment;

	@GetMapping("/lucky-word")
	public String showLuckyWord() {
		
		if (enviroment != null) {
			enviroment.getPropertySources().stream().forEach( p -> {
				System.out.println(p.getName() + " - " );
			});
		}
		
		return "The lucky word is: " + luckyWord;
	}
}

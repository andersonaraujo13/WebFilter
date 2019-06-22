package projeto.java.web.rest.webfilter.plataform.strategy;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import projeto.java.web.rest.webfilter.dominio.Credenciais;
import projeto.java.web.rest.webfilter.dominio.Post;
import projeto.java.web.rest.webfilter.dominio.Termo;
import projeto.java.web.rest.webfilter.plataform.twitter.Auth;
import projeto.java.web.rest.webfilter.plataform.twitter.Tweet;
import projeto.java.web.rest.webfilter.service.PostService;
import projeto.java.web.rest.webfilter.service.TermoService;
import projeto.java.web.rest.webfilter.utils.StringUtils;

@Component
public class TwitterStrategy implements PlataformStrategy, Serializable {

	private static final long serialVersionUID = -9208718631284543763L;
	@Autowired
	private PostService postService;
	@Autowired
	private TermoService termoService;
	
	@Override
	public void feeding(Credenciais credenciais) {
		JsonArray array = consumir(credenciais);
		List<Tweet> tweets = convertToObject(array);
		try {
			processar(tweets);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*TODO nao funciona e desisitir de tentar*/
	private Auth authentication(String apiKey, String secretKey) {
		String keyBase64 = convertToBase64(secretKey + ":" + apiKey);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");	
		headers.add("Authorization", "Basic " + keyBase64);
		
		List<String> map = new ArrayList<>();
		map.add("grant_type=client_credentials");

		
		return null;
	}

	private String convertToBase64(String key) {
		return Base64.getEncoder().encodeToString(key.getBytes());
	}
	
	private boolean processar(List<Tweet> tweets) throws ParseException {
		for(Tweet tweet : tweets){
			SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			Date date = format.parse(tweet.getCreated_at());
			Post post = new Post();
			post.setDataCadastro(new Date());
			post.setDataPostagem(date);
			post.setIdentificadorPlataforma(tweet.getId_str());
			post.setTexto(tweet.getText());
			
			List<String> termos = StringUtils.getTermos(tweet.getText());
			
			postService.salvar(post);
			
			for(String t : termos) {
				Termo termo = new Termo();
				termo.setDataCadastro(new Date());
				termo.setTermo(t);
				termo.setPost(post);
				termo.setDataPostagem(date);
				termoService.salvar(termo);
			}
		}
		
		return true;
	}
	
	private List<Tweet> convertToObject(JsonArray array){
		Iterator<JsonElement> iterator = array.iterator();
		Gson gson = new Gson();
		List<Tweet> tweets =  new ArrayList<>();
		
		while (iterator.hasNext()) {
			Tweet tweet = gson.fromJson(iterator.next(), Tweet.class);
			tweets.add(tweet);
		}
		
		return tweets;
	}
	
	private JsonArray consumir(Credenciais credenciais) {
		Client client = ClientBuilder.newClient();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String data = format.format(new Date());
		
		WebTarget myResource = client.target("https://api.twitter.com/1.1/search/tweets.json")
				.queryParam("q", "#vazajato")
				.queryParam("result_type", "recent")
				.queryParam("until", "2019-06-18")
				.queryParam("count", "10");
		Response response = myResource.request().header("Authorization",
				"Bearer AAAAAAAAAAAAAAAAAAAAAB0L%2FAAAAAAAlWrrJq0XxRY3Gkk0JLcFHqAOB0A%3D0GQlqstTv5lDBOZYdYBnrKHsG5EbewrOXJdN0TsO1asFusWnJh")
				.get();

		Gson gson = new Gson();
		JsonObject json = gson.fromJson(response.readEntity(String.class), JsonObject.class);
		JsonArray array = json.get("statuses").getAsJsonArray();
		
		client.close();
		
		return array;
	}
}

package projeto.java.web.rest.webfilter.plataform.strategy;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import projeto.java.web.rest.webfilter.dominio.Credencial;
import projeto.java.web.rest.webfilter.dominio.Parametro;
import projeto.java.web.rest.webfilter.dominio.Post;
import projeto.java.web.rest.webfilter.dominio.Termo;
import projeto.java.web.rest.webfilter.plataform.twitter.Tweet;
import projeto.java.web.rest.webfilter.plataform.twitter.TwitterVars;
import projeto.java.web.rest.webfilter.repository.ParametroRepository;
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
	@Autowired
	private ParametroRepository parametroRepository;
	
	private Long max_id;
	private Map<String, List<Parametro>> param;
	
	private static final Logger log = LoggerFactory.getLogger(TwitterStrategy.class);
	
	@Override
	public void feeding(Credencial credenciais) {
			param = credenciais.getParametros().stream().collect(Collectors.groupingBy(Parametro::getKey));
			
			Parametro count = param.get(TwitterVars.count.valor()).stream().findFirst().get();
			Parametro max_per_feed = param.get(TwitterVars.max_per_feeding.valor()).stream().findFirst().get();;

			Parametro parametroMaxId = param.get(TwitterVars.max_id.valor()).stream().findFirst().get();
			
			int max = Integer.parseInt(max_per_feed.getValue());
			int lote = Integer.parseInt(count.getValue());
			
			for(int contador = 0; contador < max; contador += lote) {
				JsonArray array = consumir(credenciais);
				List<Tweet> tweets = convertToObject(array);
				processar(tweets);
				parametroMaxId.setValue(Long.toString(max_id - 1));
			}
			
			parametroRepository.save(parametroMaxId);
		
	}
	
	private boolean processar(List<Tweet> tweets) {
		for(Tweet tweet : tweets){
			Date date = getDate(tweet.getCreated_at());
			
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
				termo.setDataPostagemMili(date.getTime());
				
				if(!termo.getTermo().isEmpty()) {
					termoService.salvar(termo);
				}
			}
			
			max_id = tweet.getId();
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
	
	private JsonArray consumir(Credencial credenciais) {
		Client client = ClientBuilder.newClient();
		
		WebTarget myResource = client.target(param.get(TwitterVars.url.valor()).stream().findFirst().get().getValue())
				.queryParam(TwitterVars.q.valor(), param.get(TwitterVars.q.valor()).stream().findFirst().get().getValue())
				.queryParam(TwitterVars.result_type.valor(), param.get(TwitterVars.result_type.valor()).stream().findFirst().get().getValue())
				.queryParam(TwitterVars.until.valor(), param.get(TwitterVars.until.valor()).stream().findFirst().get().getValue())
				.queryParam(TwitterVars.count.valor(), param.get(TwitterVars.count.valor()).stream().findFirst().get().getValue())
				.queryParam(TwitterVars.max_id.valor(), param.get(TwitterVars.max_id.valor()).stream().findFirst().get().getValue());
		Response response = myResource.request().header(TwitterVars.authorization.valor(), param.get(TwitterVars.authorization.valor()).stream().findFirst().get().getValue())
				.get();

		Gson gson = new Gson();
		JsonObject json = gson.fromJson(response.readEntity(String.class), JsonObject.class);
		JsonArray array = json.get("statuses").getAsJsonArray();
		
		client.close();
		
		return array;
	}
	
	private Date getDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		
		try {
			return format.parse(date);
		} catch (ParseException e) {
			log.info("Não foi possível converter a data " + date);
			return new Date();
		}
		
	}
}

package fr.leroymerlin.androidapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

 

import android.util.Log;

/**
 * Helper d'appel au service REST de androidapp
 * 
 */
public final class AndroHelper {

	/**
	 * Singleton.
	 */
	private static AndroHelper instance;

	/**
	 * Factory Json pour la lecture des flux.
	 */
	private static JsonFactory jsonFactory;

	/**
	 * Mapper pour la construction d'objet Java.
	 */
	private static ObjectMapper objectMapper;

	/**
	 * Handler utilisé pour effectuer les appels réseaux.
	 */
	private static ExecutorService executorService;

	/**
	 * Constrcuteur privé.
	 */
	private AndroHelper() {
		super();
	}

	/**
	 * Construit le singleton.
	 * 
	 * @return l'instance unique de la classe.
	 */
	public static AndroHelper getInstance() {
		if (null == instance) {
			instance = new AndroHelper();
		}
		executorService = Executors.newCachedThreadPool();
		jsonFactory = new JsonFactory();
		objectMapper = new ObjectMapper(jsonFactory);

		return instance;
	}
	
	/**
	 * TODO javadoc
	 * 
	 * @return
	 */
	public String getHelloWorld() {
		String url = AndroApplication.getContext().getString(R.string.helloworldurl);
		try {
			if (url == null) {
				return "error configuration";
			}
			url += "&q=leroymerlin";
			HttpClient httpClient = new DefaultHttpClient();
			HttpContext localContext = new BasicHttpContext();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpGet, localContext);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == HttpStatus.SC_OK) {
				InputStream content = response.getEntity().getContent();
				BufferedReader br = null;
				StringBuilder sb = new StringBuilder();
				String line;
				try {
					br = new BufferedReader(new InputStreamReader(content));
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					return sb.toString();
				} catch (IOException e) {
					return "HttpStatus.SC_INTERNAL_SERVER_ERROR";
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							return "HttpStatus.SC_INTERNAL_SERVER_ERROR";
						}
					}
				}

			} else if (statusCode == HttpStatus.SC_NO_CONTENT) {
				return "HttpStatus.SC_NO_CONTENT";
			} else {
				return "HttpStatus.SC_INTERNAL_SERVER_ERROR";
			}

		} catch (Exception e) {
			Log.v("System.out", "Erreur callServer " + url, e);
		}
		return "HttpStatus.SC_INTERNAL_SERVER_ERROR";
	}


}

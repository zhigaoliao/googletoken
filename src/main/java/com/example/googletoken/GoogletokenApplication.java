package com.example.googletoken;
import java.io.ByteArrayInputStream;
import java.util.*;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Sets;
import com.google.gson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@Configuration
public class GoogletokenApplication {
	@RequestMapping(value="hello",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public static Map<String, Object> hello() throws Exception  {
		Gson gson = new Gson();

		Map<String, Object> map = new HashMap<String, Object>();
		String idToken1 = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjQwMmYzMDViNzA1ODEzMjlmZjI4OWI1YjNhNjcyODM4MDZlY2E4OTMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxMDY4OTQwOTA1NzQxLWkwcG5qbmE1dGZrcXF1bGFkZnJzc3NiNnV2bTB0a3YzLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiYXVkIjoiMTA2ODk0MDkwNTc0MS1pMHBuam5hNXRma3FxdWxhZGZyc3NzYjZ1dm0wdGt2My5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMDM0OTM2NTI4OTM1OTUxMDk4NSIsImVtYWlsIjoic3l6dHRlc3QwNEBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXRfaGFzaCI6IkdDdXpERGhTZlNhWk94YlhrRm9JVUEiLCJuYW1lIjoi5ZWG5LitMDQiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUl0YnZta1dPOHZna2thenI2N25ja0VKZ09WU2RjWmQxanlJNlZYZ0xSMmc9czk2LWMiLCJnaXZlbl9uYW1lIjoiMDQiLCJmYW1pbHlfbmFtZSI6IuWVhuS4rSIsImxvY2FsZSI6InpoLUNOIiwiaWF0IjoxNjYxMzk2MzU4LCJleHAiOjE2NjEzOTk5NTh9.BsQRbNM_SGqekO-Xy-tjdXiS6azjRDskFofn4tyYFc2wBuzVLJpB3P1K6cPFIea80hOGpP71iBGhgxCYABbhGrpHRkP97FU-aSnn3vc07qac46mdKXtIopMSuind2iovP3ftEH7Q5RvgOTfjO1VfVHeoK0uCGdZqRvuAlYRH2lirrvfCO27t400Bvup1dTo84teL-uFSVuNK6vd3jJgAeRxWDQ15ZqVZyK2zN_ML0j5-tVw1-X_zoC78e_AOrn8HDMbK9PL6QQkry_yNvUKDlq7-P5HJnXA1QrYxTdUTCUIK_Qi1_nx7JLKYhYWTHtlJuew0JLmPdp7-ocRzhEofOg";
		map.put("type", "authorized_user");
		map.put("token_type", "Bearer");
		map.put("idToken", idToken1);
		map.put("refresh_token","1//0exUEKl4aD0rGCgYIARAAGA4SNwF-L9IrtNr82Gp1RAMv7WT-nkl7qc71xAKsBp7EYVFLTbnF2c4AyzDcnoD1uRJU2gJW-_b3JQA");
		map.put("client_id","1068940905741-i0pnjna5tfkqquladfrsssb6uvm0tkv3.apps.googleusercontent.com");
		map.put("client_secret","AZrisx7cWRKQb2VdN8BA_e0_");
		String json = gson.toJson(map);
		System.out.println(json);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(json.getBytes());
		Set<String> scopes = Sets.newHashSet("https://www.googleapis.com/auth/content");
		//SslUtils.ignoreSsl();
		GoogleCredentials credentials = GoogleCredentials.fromStream(byteArrayInputStream).createScoped(scopes);
		AccessToken accessToken = credentials.refreshAccessToken();
		System.out.println(accessToken.getTokenValue());

		Map<String, Object> resultMap = new HashMap<>(4);
		resultMap.put("accessToken", "Bearer " + accessToken.getTokenValue());
		//resultMap.put("expireTime", DateUtil.formatDateTime(accessToken.getExpirationTime()));
		return resultMap;

	}
	public static void main(String[] args) {
		SpringApplication.run(GoogletokenApplication.class, args);
	}

}

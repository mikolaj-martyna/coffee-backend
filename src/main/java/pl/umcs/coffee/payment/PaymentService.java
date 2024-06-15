package pl.umcs.coffee.payment;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
  // Would be better as a var in application.properties, but for now this will do
  private final String url = "https://secure.payu.com/pl/";
  private final int posId = 145227;
  private final int clientId = 145227;
  private final String secondKey = "13a980d4f851f3d9a1cfc792fb1f5e50";
  private final String clientSecret = "12f071174cb7eb79d4aac5bc2f07563f";

  public void authorize() {
    final String authRequest =
        url
            + "standard/user/oauth/authorize"
            + "?grant_type="
            + "client_credentials"
            + "&client_id="
            + clientId
            + "&client_secret="
            + clientSecret;

    final ResponseEntity<String> jsonResponse =
        new RestTemplate().postForEntity(authRequest, null, String.class);

    String token = new JSONObject(jsonResponse.getBody()).getString("access_token");
    System.out.println(token);
  }
}

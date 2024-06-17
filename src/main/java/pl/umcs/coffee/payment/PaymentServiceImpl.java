package pl.umcs.coffee.payment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import pl.umcs.coffee.order.*;
import pl.umcs.coffee.security.JwtService;
import pl.umcs.coffee.user.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
  // Shouldn't be here, but it's not prod so it'll do for now
  private final String url = "https://secure.payu.com/";
  private final int posId = 145227;
  private final int clientId = 145227;
  private final String secondKey = "13a980d4f851f3d9a1cfc792fb1f5e50";
  private final String clientSecret = "12f071174cb7eb79d4aac5bc2f07563f";
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;

  private String token;
  JwtService jwtService;

  public PaymentServiceImpl(OrderRepository orderRepository, UserRepository userRepository, JwtService jwtService) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  public String authorize() {
    final String authRequest =
        url
            + "pl/standard/user/oauth/authorize"
            + "?grant_type="
            + "client_credentials"
            + "&client_id="
            + clientId
            + "&client_secret="
            + clientSecret;

    final ResponseEntity<String> jsonResponse =
        new RestTemplate().postForEntity(authRequest, null, String.class);

    token = new JSONObject(jsonResponse.getBody()).getString("access_token");

    return token;
  }

  public String createOrder(@NotNull Order order) {
    final String orderRequest = url + "api/v2_1/orders";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setBearerAuth(getAuthorizationToken());

    JSONObject body = new JSONObject();
    body.put("continueUrl", "http://localhost:3000"); // TODO: change once prod is up
    body.put("customerIp", "1.12.123.255");
    body.put("merchantPosId", String.valueOf(posId));
    body.put("description", "Order for account " + order.getUser().getUsername());
    body.put("currencyCode", "PLN");
    body.put("totalAmount", String.valueOf(OrderServiceImpl.getTotal(order)));
    body.put("products", OrderMapper.toPayu(order));

    HttpEntity<String> request = new HttpEntity<>(body.toString(), headers);

    final ResponseEntity<String> jsonResponse =
        new RestTemplate().postForEntity(orderRequest, request, String.class);
    
    return new JSONObject(jsonResponse.getBody()).getString("redirectUri");
  }

  public String handlePayment(String token) {
    Order userOrder = orderRepository.findTopOneByUserEmail(jwtService.extractUsername(token));

    if (userOrder == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
    }

    return createOrder(userOrder);
  }

  private String getAuthorizationToken() {
    if (token == null) {
      authorize();
    }

    return token;
  }
}

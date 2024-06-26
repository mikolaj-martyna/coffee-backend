package pl.umcs.coffee.user;

import jakarta.persistence.*;
import java.util.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.umcs.coffee.cart.Cart;
import pl.umcs.coffee.order.Order;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Personal data
  private String name;
  private String surname;

  private String email;
  private String hashedPassword;

  // Address
  private String country;
  private String city;
  private String street;

  private String zipCode;

  private int buildingNumber;
  private int apartmentNumber;

  // Permissions
  private Role role = Role.USER;

  // Cart
  @OneToOne private Cart cart;

  // Order
  @OneToMany private List<Order> orders = new ArrayList<>();

  // UserDetails implementation
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.toString()));
  }

  @Override
  public String getPassword() {
    return this.hashedPassword;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

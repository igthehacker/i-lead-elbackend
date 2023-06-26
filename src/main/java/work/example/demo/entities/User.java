package work.example.demo.entities;
import jdk.jshell.Snippet;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.mail.Address;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @SequenceGenerator(
            name="user_sequence",
            sequenceName ="user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String address;
    private String phone_number;
    @Enumerated(EnumType.STRING)
    private UserRole UserRole;
    @Column(nullable = false, columnDefinition = "boolean default false")

    private boolean locked = false ;
    @Column(nullable = false, columnDefinition = "boolean default false")

    private boolean enabled = false;

    public User(
            String firstName,
            String lastName,
            String email,
            String password,
            String address,
            String phone_number,
            UserRole userRole) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone_number = phone_number;
        UserRole = userRole;

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(UserRole.name());
        return Collections.singletonList(authority);

    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public String getUsername() {
        return email;
    }

    public String getLastName() {

        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getAddress(){
        return address;
    }
    public String getPhone_number(){
        return phone_number;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


}
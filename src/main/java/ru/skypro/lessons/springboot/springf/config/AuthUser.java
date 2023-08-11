package ru.skypro.lessons.springboot.springf.config;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Setter
@Getter
@Entity
@Table(name = "auth_user")
public class   AuthUser {
//public class   AuthUser implements GrantedAuthority {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private Integer enabled ;
    @JoinColumn(name = "user_id")
    @OneToMany( fetch = FetchType.EAGER)
    private List<Authorities>authoritiesList;



}



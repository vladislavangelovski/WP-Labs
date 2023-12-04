package mk.finki.ukim.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "user_accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany
    private List<ShoppingCart> carts;

    public User(String username, String password, UserFullname userFullname, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.userFullname = userFullname;
        this.dateOfBirth = dateOfBirth;
    }


    public User(UserFullname userFullname) {
        this.userFullname = userFullname;
    }

    public User() {

    }
}

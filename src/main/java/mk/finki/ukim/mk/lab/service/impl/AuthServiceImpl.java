package mk.finki.ukim.mk.lab.service.impl;

import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.model.UserFullname;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean credentialsInvalid(String username, String password)
    {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }


    @Override
    public User login(String username, String password) {
        if(credentialsInvalid(username, password))
        {
            throw new InvalidArgumentsException();
        }
        return userRepository.findUserByUsernameAndAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name,
                         String surname, LocalDate dateOfBirth) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        UserFullname userFullname = new UserFullname();
        userFullname.setName(name);
        userFullname.setSurname(surname);
        User user = new User(username, password, userFullname, dateOfBirth);
        return userRepository.save(user);
    }

}

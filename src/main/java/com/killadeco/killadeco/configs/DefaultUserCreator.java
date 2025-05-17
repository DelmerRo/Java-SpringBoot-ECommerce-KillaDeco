package com.killadeco.killadeco.configs;

import com.killadeco.killadeco.model.User;
import com.killadeco.killadeco.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserCreator implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserCreator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("Florencia Galeassi")) {
            String defaultPassword = "12345678Pro+";
            String encodedPassword = passwordEncoder.encode(defaultPassword);

            User florencia = User.builder()
                    .username("Florencia Galeassi")
                    .password(encodedPassword)
                    .email("Florencia_Galeassi@example.com")
                    .contact("+54 351-5654563")
                    .isActive(true)
                    .role(User.Role.SUPER_ADMIN)
                    .type(User.Type.INDIVIDUAL)
                    .subscribedToNewsletter(true)
                    .wantsEmailNotifications(false)
                    .build();

            userRepository.save(florencia);
        }
    }
}

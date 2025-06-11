package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.UserEntity;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean saveNewUser(UserEntity user) {
            try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
           return true;
        }catch(Exception e){
            log.error("Error Occurred",e);
            log.info("Details",e);
            log.warn("Warning Alert ",e);
            log.debug("Debugging",e);
            log.trace("Tracing Code",e);
            return false;
        }

    }

    public void saveAmin(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(UserEntity user) {

        userRepository.save(user);
    }

    public List<UserEntity> getAll() {

        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {

        return userRepository.findById(id);
    }

    public void deleteById(Long id) {

        userRepository.deleteById(id);
    }

    @Cacheable("user")
    public UserEntity findByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }
}

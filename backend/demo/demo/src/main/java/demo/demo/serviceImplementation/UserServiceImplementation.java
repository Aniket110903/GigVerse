package demo.demo.serviceImplementation;

import demo.demo.dto.loginDTO.LoginResponse;
import demo.demo.dto.loginDTO.RegisterPayload;
import demo.demo.entities.Users;
import demo.demo.repository.UserRepository;
import demo.demo.services.UserServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation extends UserServices {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse fetchUsers(String payload) {
        Optional<Users> user = userRepository.findById(UUID.fromString(payload));
        LoginResponse res = new LoginResponse();
        if(user.isPresent()) {
            res.set_id(user.get().getId());
            res.setEmail(user.get().getEmail());
            res.setFullname(user.get().getFullName());
            res.setImg(user.get().getImg());
            res.setExpertise(user.get().getExpertise());
            res.setIsSeller(user.get().getIsSeller());
            res.setDesc(user.get().getDesc());
            res.setCreatedAt(user.get().getCreatedAt());
            res.setUpdatedAt(user.get().getUpdatedAt());
            res.set__v(user.get().get__v());
        }else{
            throw new RuntimeException("User Not Found");
        }
        return res;
    }

    @Override
    @Transactional
    public void register(RegisterPayload payload) {
        Optional<Users> exists = userRepository.findByEmail(payload.getEmail());
        if(exists.isPresent()){
            throw new RuntimeException("User Already Exists");
        }
        Users user = new Users();
        user.setFullName(payload.getFullName());
        user.setEmail(payload.getEmail());
        user.setImg(payload.getImg());
        user.setDesc(payload.getDesc());
        user.setExpertise(payload.getExpertise());
        user.setIsSeller(payload.getIsSeller());
        LocalDateTime localDateTime = LocalDateTime.parse(payload.getCreatedAt());
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        user.setCreatedAt(instant);
        localDateTime = LocalDateTime.parse(payload.getUpdatedAt());
        instant = localDateTime.toInstant(ZoneOffset.UTC);
        user.setUpdatedAt(instant);
        user.set__v(payload.get__v());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(String pyaload) {
        Optional<Users> user = userRepository.findByEmail(pyaload);
        LoginResponse res = new LoginResponse();
        if(user.isPresent()){
            res.set_id(user.get().getId());
            res.setEmail(user.get().getEmail());
            res.setFullname(user.get().getFullName());
            res.setImg(user.get().getImg());
            res.setExpertise(user.get().getExpertise());
            res.setIsSeller(user.get().getIsSeller());
            res.setDesc(user.get().getDesc());
            res.setCreatedAt(user.get().getCreatedAt());
            res.setUpdatedAt(user.get().getUpdatedAt());
            res.set__v(user.get().get__v());
        }else{
            throw new RuntimeException("User Not Found");
        }

        return res;
    }
}

package com.fintech.paymentsystem.service;
import com.fintech.paymentsystem.entity.User;
import com.fintech.paymentsystem.entity.Wallet;
import com.fintech.paymentsystem.entity.enums.UserRole;
import com.fintech.paymentsystem.repository.UserRepository;
import com.fintech.paymentsystem.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor   //Create a constructor for all final fields.(Advantage: No need for @Autowired)
public class UserService {
    private final  UserRepository userRepository;
    private final WalletRepository walletRepository;

    // Create a new user and automatically create a wallet for them.
    @Transactional
    public User createUser(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        user.setRole(UserRole.USER);
        user.setEnabled(true);

        User savedUser=userRepository.save(user);

        // Create a wallet for the new user
        Wallet wallet= Wallet.builder()
                .user(savedUser)
                .balance(BigDecimal.ZERO)
                .blockedBalance(BigDecimal.ZERO)
                .usedCredit(BigDecimal.ZERO)
                .build();
        walletRepository.save(wallet);

        return savedUser;
    }

    //Get all users.
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found with id"+ id));
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User not found with username :"+ username));
    }

}

package nehe.chatappapi.Services;


import nehe.chatappapi.Repositories.UserRepository;
import nehe.chatappapi.security.MyUserPrincpal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    public UserDetailsService(){}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var user = userRepository.findByEmail(email);

        if(user ==null) throw new UsernameNotFoundException("User with email: "+ email + "not found");

        return new MyUserPrincpal(user);
    }
}

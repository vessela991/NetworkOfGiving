package NetworkOfGiving.Server.Features.Identity.Service;

import NetworkOfGiving.Server.Data.User;
import NetworkOfGiving.Server.Features.User.UserRepository;
import NetworkOfGiving.Server.Features.Identity.Service.Models.UserPrincipal;
import NetworkOfGiving.Server.Infrastucture.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class IdentityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new UserPrincipal(user);
    }

    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return null;
        return userRepository.save(user);
    }

    public int getUserId(String token) {
        return jwtUtil.getUserId(token.substring(7));
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtUtil.generateToken(loadUserByUsername(username));
    }
}

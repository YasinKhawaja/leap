package edu.ap.group10.leapwebapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import edu.ap.group10.leapwebapp.user.UserRepository;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;
import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import edu.ap.group10.leapwebapp.user.UserLeap;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UseradminRepository useradminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserLeap userLeap = userRepository.findByUsername(username);
        final Useradmin useradmin = useradminRepository.findByUsername(username);
        if (userLeap == null && useradmin == null){
            throw new UsernameNotFoundException(username);
        }
        return userLeap;
    }
    
}

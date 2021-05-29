package edu.ap.group10.leapwebapp.userleap;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;
import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
@Service
public class UserLeapDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserLeapRepository userRepository;

    @Autowired
    private UseradminRepository useradminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserLeap userLeap = userRepository.findByUsername(username);
        final Useradmin useradmin = useradminRepository.findByUsername(username);
        if (userLeap == null && useradmin == null){
            throw new UsernameNotFoundException(username);
        }
        else if (userLeap != null){
            return userLeap;
        }
        else{
            return useradmin;
        }
    }
}*/

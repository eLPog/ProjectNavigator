package wsb2023.pogorzelski.config;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.repositories.PersonRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    public final PersonRepository personRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Person person = personRepository.findByUsername(username).orElse(null);

        if (person == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = getUserAuthorities(person);

        return new User(person.getUsername(),
                person.getPassword(),
                authorities);
    }

    private List<GrantedAuthority> getUserAuthorities(Person person) {

        List<Authority> authorities = person.getAuthorities();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority a : authorities) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(a.getAuthority());
            grantedAuthorities.add(simpleGrantedAuthority);
        }


        return new ArrayList<>(grantedAuthorities);
    }

}

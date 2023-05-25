package wsb2023.pogorzelski.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.models.Authority;
import wsb2023.pogorzelski.repositories.AuthorityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorityService {

    final private AuthorityRepository authorityRepository;


    public List<Authority> findAll(){
        return authorityRepository.findAll();
    };

    public void addRoleToUser(Long userId, Long authorityId){
        authorityRepository.setAuthorityToUser(userId,authorityId);
    }

    public void removeRoleFromUser(Long userId, String authorityName){
        Authority auth = authorityRepository.findByAuthority(authorityName).orElseThrow();
            authorityRepository.removeAuthorityFromUser(userId,auth.getId());
    }

}

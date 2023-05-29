package wsb2023.pogorzelski.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wsb2023.pogorzelski.config.CustomUserDetailsService;
import wsb2023.pogorzelski.models.Person;
import wsb2023.pogorzelski.models.UserEditObject;
import wsb2023.pogorzelski.repositories.PersonRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonService {

    final private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void addNewUser(Person person) {
        this.personRepository.save(person);
    }

    public Person findUserById(Long id) {
        return this.personRepository.findById(id).orElseThrow();
    }

    ;

    public Person findUserByName(String username) {
        return this.personRepository.findByUsername(username).orElseThrow();
    }

    public List<String> getUserAuthorities(Long userId) {
        return personRepository.getAuthoritiesAndUsernames(userId);
    }

    public void editUserData(UserEditObject userEditObject) {
        Person userOriginal = this.getLoggedUser();

        if (userEditObject.getNewPassword() != null) {
            String hashedPass = userOriginal.getPassword();
            String notHashedPass = userEditObject.getPassword();
            Boolean isUserAuth = confirmOriginalAndNewPassword(hashedPass,notHashedPass);
            if(isUserAuth){
                userOriginal.setPassword(new BCryptPasswordEncoder().encode(userEditObject.getNewPassword()));
            }
        }

        userOriginal.setUsername(userEditObject.getUsername());
        userOriginal.setRealName(userEditObject.getRealName());
        userOriginal.setEmail(userEditObject.getEmail());
        personRepository.save(userOriginal);
    }

    private Boolean confirmOriginalAndNewPassword(String hashedPass, String notHashedPass){
        return  new BCryptPasswordEncoder().matches(notHashedPass, hashedPass);
    }

    private String checkLoggedUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    ;

    public Person getLoggedUser() {
        String userName = this.checkLoggedUserName();
        return this.findUserByName(userName);
    }


}

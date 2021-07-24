package desafio.calcme.request;

import desafio.calcme.database.User;
import desafio.calcme.database.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class Register {

    @Autowired
    private UserRepository userRepository;

    private final Logger log = LoggerFactory.getLogger(Register.class);
    @GetMapping("/")
    public String returnt() {
        return "API v1";
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String nome, @RequestParam String email, @RequestParam String telefone){
       try {
           userRepository.save(new User(nome, email, telefone));

           return ResponseEntity.ok().body("{\"Status\": \"OK\"}");
       }catch (Exception ex){
           return ResponseEntity.status(500).body("{\"Status\": \"Error\"," +
                   "\"Error:\" "+ex.getMessage()+"}");
       }
    }
}

package br.com.dicas.api_test.services.imp;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.repositories.UserRepository;
import br.com.dicas.api_test.services.UserService;
import br.com.dicas.api_test.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado!"));
    }
}

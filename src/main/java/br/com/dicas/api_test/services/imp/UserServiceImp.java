package br.com.dicas.api_test.services.imp;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;
import br.com.dicas.api_test.repositories.UserRepository;
import br.com.dicas.api_test.services.UserService;
import br.com.dicas.api_test.services.exceptions.DataIntegrateViolationException;
import br.com.dicas.api_test.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado!"));
    }
    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User updata(UserDTO obj) {

        return repository.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDTO obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if (user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegrateViolationException("Email já cadastrado no sistema!");
        }
    }
}

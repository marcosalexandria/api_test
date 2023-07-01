package br.com.dicas.api_test.services;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
    User updata(UserDTO obj);
}

package br.com.dicas.api_test.services;

import br.com.dicas.api_test.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    List<User> findAll();

}

package br.com.dicas.api_test.services;

import br.com.dicas.api_test.domain.User;

public interface UserService {
    User findById(Integer id);
}

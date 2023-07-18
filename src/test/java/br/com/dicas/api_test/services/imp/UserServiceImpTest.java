package br.com.dicas.api_test.services.imp;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;
import br.com.dicas.api_test.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {

    // --- CONSTANTES CRIADAS SÓ PARA NÃO DUPLICAR CODIGO NO METODO startUser
    private static final Integer ID      = 1;
    private static  final String NAME    = "Valdir";
    private static final String EMAIL    = "valdir@email.com";
    private static final String PASSWORD = "123";

    // --- TODAS AS CLASSES QUE UserServiceImp UTILIZA

    @InjectMocks // --- Cria uma instacia real
    private UserServiceImp service;

    @Mock // --- Cria uma instancia fake
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    // --- ANTES DE TUDO FAÇA ISSO
    @BeforeEach
    void setUp() {
        // --- INICIE OS MOCKS DESSA CLASSE
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
        // -- MOCANDO O repository.findById()
        // -- QUANDO O repository.findById FOR CHAMADO COM NUMERO INTEIRO, ENTÃO RETORNE O optionalUser
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        // -- CHAMANDO O METODO TESTADO
        User response = service.findById(ID);
        // -- ASSEGURA QUE NAO É Null E (O QUE ESPERA, O QUE VEM)
        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void updata() {
    }

    @Test
    void delete() {
    }

    // --- MÉTODO PARA INICIAR ESSAS INSTANCIAS
    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}
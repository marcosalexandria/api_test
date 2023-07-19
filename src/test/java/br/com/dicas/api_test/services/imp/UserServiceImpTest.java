package br.com.dicas.api_test.services.imp;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;
import br.com.dicas.api_test.repositories.UserRepository;
import br.com.dicas.api_test.services.exceptions.DataIntegrateViolationException;
import br.com.dicas.api_test.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
    void findByIdObjectNotFoundException(){
        // -- QUANDO O repository.findById FOR CHAMADO COM NUMERO INTEIRO, ENTÃO LANCE ObjectNotFoundException
        Mockito.when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Objeto Não Encontrado!"));
        try {
            service.findById(ID);
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class , ex.getClass());
            Assertions.assertEquals("Objeto Não Encontrado!", ex.getMessage());
        }
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user)); //-- MOCANDO

        List<User> response = service.findAll();

        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());    //-- NO MOCANDO, FOI PASSADO APENAS UM USER
        Assertions.assertEquals(User.class, response.get(0).getClass());
        Assertions.assertEquals(ID, response.get(0).getId());
        Assertions.assertEquals(NAME, response.get(0).getName());
        Assertions.assertEquals(EMAIL, response.get(0).getEmail());
        Assertions.assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void create() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(user);

        User response = service.create(userDTO);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());
    }
    @Test
    void createDataIntegrateViolationException(){
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception ex){
            Assertions.assertEquals(DataIntegrateViolationException.class, ex.getClass());
            Assertions.assertEquals("Email já cadastrado no sistema!", ex.getMessage());
        }
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
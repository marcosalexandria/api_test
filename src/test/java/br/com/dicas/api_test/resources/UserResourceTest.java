package br.com.dicas.api_test.resources;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;
import br.com.dicas.api_test.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest
public class UserResourceTest {

    // --- CONSTANTES CRIADAS SÓ PARA NÃO DUPLICAR CODIGO NO METODO startUser
    private static final Integer ID      = 1;
    private static  final String NAME    = "Valdir";
    private static final String EMAIL    = "valdir@email.com";
    private static final String PASSWORD = "123";

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById(){
        Mockito.when(service.findById(Mockito.anyInt())).thenReturn(user);
        Mockito.when(mapper.map(Mockito.any(), Mockito.any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(ResponseEntity.class, response.getClass());
        Assertions.assertEquals(UserDTO.class, response.getBody().getClass());

        Assertions.assertEquals(response.getBody().getId(), ID);
        Assertions.assertEquals(response.getBody().getName(), NAME);
        Assertions.assertEquals(response.getBody().getEmail(), EMAIL);
        Assertions.assertEquals(response.getBody().getPassword(), PASSWORD);
    }

    // --- MÉTODO PARA INICIAR ESSAS INSTANCIAS
    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}

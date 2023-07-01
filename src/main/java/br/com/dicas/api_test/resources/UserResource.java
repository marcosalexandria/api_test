package br.com.dicas.api_test.resources;

import br.com.dicas.api_test.domain.User;
import br.com.dicas.api_test.domain.dto.UserDTO;
import br.com.dicas.api_test.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserResource {
    @Autowired
    ModelMapper mapper;
    @Autowired
    UserService service;

    private static final String ID = "/{id}";

    @GetMapping(value = ID)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok()
                .body(service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj){
        User user = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path(ID).buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = ID)
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody UserDTO obj){
        obj.setId(id);
        User user = service.updata(obj);
        return ResponseEntity.ok().body(mapper.map(user, UserDTO.class));
    }
    @DeleteMapping(value = ID)
    public ResponseEntity<UserDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

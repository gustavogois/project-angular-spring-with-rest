package pt.gois.algaworks.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.gois.algaworks.algamoneyapi.model.Categoria;
import pt.gois.algaworks.algamoneyapi.repository.CategoriaRepository;

import java.util.List;

/**
 * Created by Gustavo on 14/08/2017.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {

        return categoriaRepository.findAll();
    }


//    // Instructor suggests previous approach to returning an empty list
//    @GetMapping
//    public ResponseEntity<?> listar() {
//
//        List<Categoria> categorias = categoriaRepository.findAll();
//        // 404 - Not Found. Not the most suitable, would indicate that didn't find the resource, URL has changed,
//        // wich is not the case...
//        // return categorias.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categorias);
//        // 204 - starts with '2', what means that the response is fine. But, 204 indicates that it´s fine, but,
//        // there isn´t nothing to show.
//        return categorias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(categorias);
//    }

}
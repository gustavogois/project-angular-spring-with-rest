package pt.gois.algaworks.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
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

}

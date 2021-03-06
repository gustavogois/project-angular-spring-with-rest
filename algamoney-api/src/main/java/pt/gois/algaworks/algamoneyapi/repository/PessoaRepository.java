package pt.gois.algaworks.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.gois.algaworks.algamoneyapi.model.Categoria;
import pt.gois.algaworks.algamoneyapi.model.Pessoa;

/**
 * Created by Gustavo on 14/08/2017.
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

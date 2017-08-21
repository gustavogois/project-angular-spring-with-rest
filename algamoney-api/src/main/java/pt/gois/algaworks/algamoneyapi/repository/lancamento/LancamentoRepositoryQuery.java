package pt.gois.algaworks.algamoneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.gois.algaworks.algamoneyapi.model.Lancamento;
import pt.gois.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

/**
 * Created by Gustavo on 20/08/2017.
 */
public interface LancamentoRepositoryQuery {
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
}

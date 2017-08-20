package pt.gois.algaworks.algamoneyapi.repository.lancamento;

import pt.gois.algaworks.algamoneyapi.model.Lancamento;
import pt.gois.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

/**
 * Created by Gustavo on 20/08/2017.
 */
public interface LancamentoRepositoryQuery {
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}

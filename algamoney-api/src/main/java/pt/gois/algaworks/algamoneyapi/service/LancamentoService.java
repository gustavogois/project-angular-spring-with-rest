package pt.gois.algaworks.algamoneyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.gois.algaworks.algamoneyapi.model.Lancamento;
import pt.gois.algaworks.algamoneyapi.model.Pessoa;
import pt.gois.algaworks.algamoneyapi.repository.LancamentoRepository;
import pt.gois.algaworks.algamoneyapi.repository.PessoaRepository;
import pt.gois.algaworks.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

/**
 * Created by Gustavo on 20/08/2017.
 */
@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento save(Lancamento lancamento) {

        Pessoa pessoa = pessoaRepository.getOne(lancamento.getPessoa().getCodigo());
        if(pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);
    }
}

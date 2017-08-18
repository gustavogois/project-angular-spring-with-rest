package pt.gois.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pt.gois.algaworks.algamoneyapi.model.Pessoa;
import pt.gois.algaworks.algamoneyapi.repository.PessoaRepository;

/**
 * Created by Gustavo on 18/08/2017.
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = getPessoaByCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoa = getPessoaByCodigo(codigo);
        pessoa.setAtivo(ativo);
        pessoaRepository.save(pessoa);
    }

    private Pessoa getPessoaByCodigo(Long codigo) {
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        if(pessoa == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoa;
    }

}

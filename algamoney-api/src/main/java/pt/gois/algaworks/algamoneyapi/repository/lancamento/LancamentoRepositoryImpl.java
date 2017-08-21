package pt.gois.algaworks.algamoneyapi.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import pt.gois.algaworks.algamoneyapi.model.Lancamento;
import pt.gois.algaworks.algamoneyapi.model.Lancamento_;
import pt.gois.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gustavo on 20/08/2017.
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);

        Root<Lancamento> root = criteria.from(Lancamento.class);

        // Criar as restrições
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<Lancamento>(query.getResultList(), pageable, total(lancamentoFilter));

    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
                                        Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Lancamento_.descricao)), "%" +
                            lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }
        if(lancamentoFilter.getDataDeVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(
                    root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataDeVencimentoDe()));
        }
        if(lancamentoFilter.getDataDeVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(
                    root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataDeVencimentoAte()));

        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    private Long total(LancamentoFilter lancamentoFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionaRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {

        int pageAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = pageAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
}

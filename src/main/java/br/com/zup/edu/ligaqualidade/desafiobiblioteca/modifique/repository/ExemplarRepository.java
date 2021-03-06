package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.util.Set;
import java.util.stream.Collectors;

public class ExemplarRepository {

    Set<DadosExemplar> exemplares;

    public ExemplarRepository(Set<DadosExemplar> exemplares) {
        this.exemplares = exemplares;
    }

    public Set<Integer> getIds(int idLivro, TipoExemplar tipoExemplar) {
        return exemplares.stream().filter(it -> it.tipo == tipoExemplar && it.idLivro == idLivro).map(it -> it.idExemplar).collect(Collectors.toSet());
    }

    public Integer getId(Integer idLivro, TipoExemplar tipoExemplar, EmprestimoConcedidoRepository emprestimoConcedidoRepository) {

        Set<Integer> todosIdsExemplares = this.getIds(idLivro, tipoExemplar);
        Set<Integer> todosExemplaresComEmprestimoAtivo = emprestimoConcedidoRepository.getExemplaresComEmprestivoAtivos(todosIdsExemplares);
        Set<Integer> todosExemplaresDisponiveis = todosIdsExemplares.stream().filter(it -> !todosExemplaresComEmprestimoAtivo.contains(it)).collect(Collectors.toSet());

        return todosExemplaresDisponiveis.stream().findFirst().get();
    }

}

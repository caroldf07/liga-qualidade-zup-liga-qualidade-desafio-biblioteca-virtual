package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//8
public class EmprestimoConcedidoRepository {

    Set<EmprestimoConcedido> emprestimosConcedidos;

    public EmprestimoConcedidoRepository() {
        emprestimosConcedidos = new HashSet<>();
    }

    public Integer getId(Integer idLivro, TipoExemplar tipoExemplar, ExemplarRepository exemplarRepository) {

        Set<Integer> todosIdsExemplares = exemplarRepository.getIds(idLivro, tipoExemplar);
        Set<Integer> todosExemplaresComEmprestimoAtivo = this.getExemplaresComEmprestivoAtivos(todosIdsExemplares);

        //1
        Set<Integer> todosExemplaresDisponiveis = todosIdsExemplares.stream().filter(it -> !todosExemplaresComEmprestimoAtivo.contains(it)).collect(Collectors.toSet());

        return todosExemplaresDisponiveis.stream().findFirst().get();
    }

    public void registrar(Set<DadosEmprestimo> emprestimos, UsuarioRepository usuarioRepository,
                          ExemplarRepository exemplarRepository) {

        //1
        for (DadosEmprestimo emprestimo : emprestimos) {

            //1
            if (emprestimo.tempo <= 60) {
                continue;
            }

            //1
            DadosUsuario dadosUsuario = usuarioRepository.get(emprestimo.idUsuario);

            //1
            if (TipoUsuario.PESQUISADOR.equals(dadosUsuario.padrao) && TipoExemplar.RESTRITO.equals(emprestimo.tipoExemplar)) {
                continue;
            }

            Integer idExemplar = exemplarRepository.getIds(emprestimo.idLivro,
                    emprestimo.tipoExemplar).stream().findFirst().get();

            emprestimosConcedidos.add(new EmprestimoConcedido(emprestimo.idPedido, emprestimo.idUsuario,
                    idExemplar,
                    LocalDate.now().plusDays(emprestimo.tempo)));
        }
    }

    //1
    public void devolver(Integer idEmprestimo) {
        emprestimosConcedidos.stream().filter(it -> it.idEmprestimo == idEmprestimo).findFirst().get().registraDevolucao();
    }

    public Set<EmprestimoConcedido> get() {
        return emprestimosConcedidos;
    }

    public Set<Integer> getExemplaresComEmprestivoAtivos(Set<Integer> idsExemplares) {

        //2
        return emprestimosConcedidos.stream().filter(it ->
                !it.getMomentoDevolucao().isPresent() && idsExemplares.contains(it.idExemplar)
        ).map(it -> it.idExemplar).collect(Collectors.toSet());
    }
}

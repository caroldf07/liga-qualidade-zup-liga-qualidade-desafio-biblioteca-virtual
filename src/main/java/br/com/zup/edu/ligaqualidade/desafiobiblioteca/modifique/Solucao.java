package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosDevolucao;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository.EmprestimoConcedidoRepository;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository.ExemplarRepository;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository.UsuarioRepository;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.service.DevolverEmprestimoService;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosLivro;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;

import java.time.LocalDate;
import java.util.Set;

public class Solucao {

    /**
     * Você precisa implementar o código para executar o fluxo
     * o completo de empréstimo e devoluções a partir dos dados
     * que chegam como argumento.
     *
     * Caso você queira pode adicionar coisas nas classes que já existem,
     * mas não pode alterar nada.
     */

    /**
     * @param livros                            dados necessários dos livros
     * @param exemplares                        tipos de exemplares para cada livro
     * @param usuarios                          tipos de usuarios
     * @param emprestimos                       informações de pedidos de empréstimos
     * @param devolucoes                        informações de devoluções, caso exista.
     * @param dataParaSerConsideradaNaExpiracao aqui é a data que deve ser utilizada para verificar expiração
     * @return
     */

    //6
    public static Set<EmprestimoConcedido> executa(Set<DadosLivro> livros,
                                                   Set<DadosExemplar> exemplares,
                                                   Set<DadosUsuario> usuarios,
                                                   Set<DadosEmprestimo> emprestimos,
                                                   Set<DadosDevolucao> devolucoes,
                                                   LocalDate dataParaSerConsideradaNaExpiracao) {

        //1
        UsuarioRepository usuarioRepository = new UsuarioRepository(usuarios);

        //1
        ExemplarRepository exemplarRepository = new ExemplarRepository(exemplares);

        //1
        EmprestimoConcedidoRepository emprestimoConcedidoRepository = new EmprestimoConcedidoRepository();

        //1
        if (!emprestimos.isEmpty()) {
            emprestimoConcedidoRepository.registrar(emprestimos, usuarioRepository, exemplarRepository);
        }

        //1
        DevolverEmprestimoService devolverEmprestimoService = new DevolverEmprestimoService(emprestimoConcedidoRepository);

        //1
        if (!devolucoes.isEmpty()) {
            devolverEmprestimoService.devolver(devolucoes);
        }

        return emprestimoConcedidoRepository.get();
    }

}

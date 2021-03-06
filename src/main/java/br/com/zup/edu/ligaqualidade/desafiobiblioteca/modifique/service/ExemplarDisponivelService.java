package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.service;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository.EmprestimoConcedidoRepository;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique.repository.ExemplarRepository;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;

import java.util.Set;
import java.util.stream.Collectors;

public class ExemplarDisponivelService {

    ExemplarRepository exemplarRepository;
    EmprestimoConcedidoRepository emprestimoConcedidoRepository;

    public ExemplarDisponivelService(ExemplarRepository exemplarRepository,
            EmprestimoConcedidoRepository emprestimoConcedidoRepository){
        this.exemplarRepository = exemplarRepository;
        this.emprestimoConcedidoRepository = emprestimoConcedidoRepository;
    }


}

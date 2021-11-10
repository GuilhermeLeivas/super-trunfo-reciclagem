package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;
import br.com.leivas.supertrunforeciclagem.service.ISuperTrunfoReciclagem;
import br.com.leivas.supertrunforeciclagem.service.SuperTrunfoDaReciclagem;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe main na qual é feita a simulação
 * de uma partida de Super Trunfo da Reciclagem.
 */
public class SuperTrunfoDaReciclagemSimulacao {

    public static void main(String[] args) {

        final ISuperTrunfoReciclagem facadeSuperTrunfo = new SuperTrunfoDaReciclagem();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do jogador 1\n");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do jogador 2\n");
        String nomeJogador2 = scanner.nextLine();
        facadeSuperTrunfo.iniciaJogo(nomeJogador1, nomeJogador2, Rodada.TipoRodada.TIPO);
        while (facadeSuperTrunfo.getStatusJogo() != ISuperTrunfoReciclagem.StatusJogo.FINALIZADO) {
            Optional<Jogador> jogador = facadeSuperTrunfo.vencedorUltimaRodada();
            System.out.println(jogador.isPresent() ? String.format("Vencedor da rodada %s\n", jogador.get()) : "Empate na rodada\n");
            jogador.ifPresentOrElse(jogadorVencedor -> {
                System.out.println("Vencedor da rodada " + jogadorVencedor);
                facadeSuperTrunfo.proximaJogada(jogadorVencedor.escolherTipoRodada());
            }, () -> System.out.println(""));

        }

    }
}

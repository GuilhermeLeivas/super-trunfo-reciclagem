package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;
import br.com.leivas.supertrunforeciclagem.service.ISuperTrunfo;
import br.com.leivas.supertrunforeciclagem.service.SuperTrunfoDaReciclagem;
import br.com.leivas.supertrunforeciclagem.util.RodadaUtil;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe main na qual é feita a simulação
 * de uma partida de Super Trunfo da Reciclagem.
 */
public class SuperTrunfoDaReciclagemSimulacao {

    public static void main(String[] args) {

        final ISuperTrunfo facadeSuperTrunfo = new SuperTrunfoDaReciclagem();
        final RodadaUtil rodadaUtil = new RodadaUtil();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do player 1\n");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do player 2\n");
        String nomeJogador2 = scanner.nextLine();
        facadeSuperTrunfo.iniciaJogo(nomeJogador1, nomeJogador2,  rodadaUtil.randomTipoRodada());
        while (facadeSuperTrunfo.getStatusJogo() != ISuperTrunfo.StatusJogo.FINALIZADO) {
            Optional<Jogador> jogadorVencedor = facadeSuperTrunfo.vencedorUltimaRodada();
            Rodada.TipoRodada tipoProximaRodada;
            if (jogadorVencedor.isPresent()) {
                tipoProximaRodada = jogadorVencedor.get().escolherRodadaRandomicamente();
            } else {
                Jogador ultimoVencedor = facadeSuperTrunfo.ultimoVencedorPartida();
                tipoProximaRodada = ultimoVencedor != null ?
                        ultimoVencedor.escolherRodadaRandomicamente()
                        : rodadaUtil.randomTipoRodada();
            }
            facadeSuperTrunfo.proximaJogada(tipoProximaRodada);
        }
    }
}

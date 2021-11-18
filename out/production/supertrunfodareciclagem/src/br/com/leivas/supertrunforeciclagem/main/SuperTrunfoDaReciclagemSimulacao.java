package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.model.Jogador;
import br.com.leivas.supertrunforeciclagem.model.Rodada;
import br.com.leivas.supertrunforeciclagem.service.ISuperTrunfo;
import br.com.leivas.supertrunforeciclagem.service.SuperTrunfoDaReciclagem;
import br.com.leivas.supertrunforeciclagem.util.RodadaUtil;

import java.util.Optional;

/**
 * Classe main na qual é feita a simulação
 * de uma partida de Super Trunfo da Reciclagem.
 */
public class SuperTrunfoDaReciclagemSimulacao {

    public static String main(String n1, String n2) {

        String nomeJogador1 = n1;
        String nomeJogador2 = n2;
        Jogador ultimoVencedor = null;

        final ISuperTrunfo facadeSuperTrunfo = new SuperTrunfoDaReciclagem();
       /* Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do player 1\n");
        String nomeJogador1 = scanner.nextLine();
        System.out.println("Digite o nome do player 2\n");
        String nomeJogador2 = scanner.nextLine();*/
        facadeSuperTrunfo.iniciaJogo(nomeJogador1, nomeJogador2, Rodada.TipoRodada.DECOMPOSICAO);
        while (facadeSuperTrunfo.getStatusJogo() != ISuperTrunfo.StatusJogo.FINALIZADO) {
            Optional<Jogador> jogadorVencedor = facadeSuperTrunfo.vencedorUltimaRodada();
            Rodada.TipoRodada tipoProximaRodada;
            if (jogadorVencedor.isPresent()) {
                tipoProximaRodada = jogadorVencedor.get().escolherRodadaRandomicamente();
            } else {
                 ultimoVencedor = facadeSuperTrunfo.ultimoVencedorPartida();
                tipoProximaRodada = ultimoVencedor != null ?
                        ultimoVencedor.escolherRodadaRandomicamente()
                        : new RodadaUtil().randomTipoRodada();
            }
            facadeSuperTrunfo.proximaJogada(tipoProximaRodada);
        }
    return facadeSuperTrunfo.getVencedorPartida().getNome();
    }
}

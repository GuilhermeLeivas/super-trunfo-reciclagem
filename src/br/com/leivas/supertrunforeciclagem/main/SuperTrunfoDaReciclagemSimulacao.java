package br.com.leivas.supertrunforeciclagem.main;

import br.com.leivas.supertrunforeciclagem.model.Rodada;
import br.com.leivas.supertrunforeciclagem.service.ISuperTrunfoReciclagem;
import br.com.leivas.supertrunforeciclagem.service.SuperTrunfoDaReciclagem;
import br.com.leivas.supertrunforeciclagem.util.RodadaUtil;

/**
 * Classe main do sistema.
 */
public class SuperTrunfoDaReciclagemSimulacao {

    private final ISuperTrunfoReciclagem novoJogo;

    public SuperTrunfoDaReciclagemSimulacao() {
        this.novoJogo = new SuperTrunfoDaReciclagem();
    }

    public static void main(String[] args) {
//
//        SuperTrunfoDaReciclagem novoJogo = new SuperTrunfoDaReciclagem();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Digite o nome do jogador 1\n");
//        String nomeJogador1 = scanner.nextLine();
//        System.out.println("Digite o nome do jogador 2\n");
//        String nomeJogador2 = scanner.nextLine();
//
//        while (novoJogo.getStatusJogo() != SuperTrunfoDaReciclagem.StatusJogo.FINALIZADO) {
//
//        }
        RodadaUtil rodadaUtil = new RodadaUtil();
        int count = 1;
        while (count < 200) {
            Rodada.TipoRodada tipo = rodadaUtil.randomTipoRodada();
            System.out.println(tipo);
            count++;
        }
    }
}

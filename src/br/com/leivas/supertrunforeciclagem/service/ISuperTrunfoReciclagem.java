package br.com.leivas.supertrunforeciclagem.service;

import br.com.leivas.supertrunforeciclagem.model.Rodada;

public interface ISuperTrunfoReciclagem {

    void iniciaJogo(String nomeJogador1, String nomeJogador2, Rodada.TipoRodada tipoPrimeiraRodada);

    void proximaJogada(Rodada.TipoRodada tipoRodada);

    void verificaTerminoJogo();
}

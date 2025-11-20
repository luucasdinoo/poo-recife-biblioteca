package br.recife.biblioteca.repository.impl;

import br.recife.biblioteca.model.lib.MidiaDigital;
import br.recife.biblioteca.repository.MidiaDigitalRepository;

import java.util.*;

public class MidiaDigitalRepositoryImpl implements MidiaDigitalRepository {

    private static final Map<Long, MidiaDigital> midiasDigitais = new HashMap<>();

    @Override
    public void salvar(MidiaDigital midiaDigital) {
        this.midiasDigitais.put(midiaDigital.getId(), midiaDigital);
    }

    @Override
    public Optional<MidiaDigital> buscarPorId(Long id) {
        return Optional.ofNullable(this.midiasDigitais.get(id));
    }

    @Override
    public List<MidiaDigital> buscarTodos() {
        return new ArrayList<>(this.midiasDigitais.values());
    }

    @Override
    public void remover(Long id) {
        this.midiasDigitais.remove(id);
    }
}

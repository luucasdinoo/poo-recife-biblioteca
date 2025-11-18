package br.recife.biblioteca.util;

import java.util.concurrent.ThreadLocalRandom;

public final class IdUtils {

    public static long gerarId(){
        return Math.abs(ThreadLocalRandom.current().nextLong());
    }
}

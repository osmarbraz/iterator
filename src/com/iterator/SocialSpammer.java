package com.iterator;

/**
 * Aplicação de envio de mensagens.
 *
 * Aqui temos outro truque útil: você pode passar um iterador para uma classe
 * cliente ao invés de dar acesso a ela à uma coleção completa. Dessa forma,
 * você não expõe a coleção ao cliente.
 *
 * E tem outro benefício: você pode mudar o modo que o cliente trabalha com a
 * coleção no tempo de execução ao passar a ele um iterador diferente. Isso é
 * possível porque o código cliente não é acoplado às classes iterador
 * concretas.
 */
public class SocialSpammer {

    public SocialNetwork network;
    public ProfileIterator iterator;

    public SocialSpammer(SocialNetwork network) {
        this.network = network;
    }

    public void sendSpamToFriends(String profileEmail, String message) {
        System.out.println("\nIterating over friends...\n");
        iterator = network.createFriendsIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendSpamToCoworkers(String profileEmail, String message) {
        System.out.println("\nIterating over coworkers...\n");
        iterator = network.createCoworkersIterator(profileEmail);
        while (iterator.hasNext()) {
            Profile profile = iterator.getNext();
            sendMessage(profile.getEmail(), message);
        }
    }

    public void sendMessage(String email, String message) {
        System.out.println("Sent message to: '" + email + "'. Message body: '" + message + "'");
    }
}

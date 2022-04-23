package com.iterator.socialnetworks;

import com.iterator.profile.Profile;
import com.iterator.iterators.LinkedInIterator;
import com.iterator.iterators.ProfileIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Cada coleção concreta é acoplada a um conjunto de classes iterador concretas
 * que ela retorna. Mas o cliente não é, uma vez que a assinatura desses métodos
 * retorna interfaces de iterador.
 */
public class LinkedIn implements SocialNetwork {

    private List<Profile> contacts;

    public LinkedIn(List<Profile> cache) {
        if (cache != null) {
            this.contacts = cache;
        } else {
            this.contacts = new ArrayList<>();
        }
    }

    public Profile requestContactInfoFromLinkedInAPI(String profileEmail) {
        // Aqui seria uma solicitação POST para um dos endpoints da API do LinkedIn.
        // Em vez disso, emulamos uma longa conexão de rede, o que você esperaria
        // na vida real...
        simulateNetworkLatency();
        System.out.println("LinkedIn: Carregando perfil '" + profileEmail + "' pela rede...");

        // ...and return test data.
        return findContact(profileEmail);
    }

    public List<String> requestRelatedContactsFromLinkedInAPI(String profileEmail, String contactType) {
        // Aqui seria uma solicitação POST para um dos endpoints da API do LinkedIn.
        // Em vez disso, emulamos uma longa conexão de rede, o que você esperaria
        // na vida real.
        simulateNetworkLatency();
        System.out.println("LinkedIn: Carregando '" + contactType + "' lista de '" + profileEmail + "' pela rede...");

        // ...and return test data.
        Profile profile = findContact(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findContact(String profileEmail) {
        for (Profile profile : contacts) {
            if (profile.getEmail().equals(profileEmail)) {
                return profile;
            }
        }
        return null;
    }

    private void simulateNetworkLatency() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new LinkedInIterator(this, "friends", profileEmail);
    }

    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new LinkedInIterator(this, "coworkers", profileEmail);
    }
}

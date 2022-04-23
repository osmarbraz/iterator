package com.iterator.socialnetworks;

import com.iterator.profile.Profile;
import com.iterator.iterators.FacebookIterator;
import com.iterator.iterators.ProfileIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Cada coleção concreta é acoplada a um conjunto de classes iterador concretas
 * que ela retorna. Mas o cliente não é, uma vez que a assinatura desses métodos
 * retorna interfaces de iterador.
 */
public class Facebook implements SocialNetwork {

    private List<Profile> profiles;

    public Facebook(List<Profile> cache) {
        if (cache != null) {
            this.profiles = cache;
        } else {
            this.profiles = new ArrayList<>();
        }
    }

    public Profile requestProfileFromFacebook(String profileEmail) {
        // Here would be a POST request to one of the Facebook API endpoints.
        // Instead, we emulates long network connection, which you would expect
        // in the real life...
        simulateNetworkLatency();
        System.out.println("Facebook: Carregando perfil '" + profileEmail + "' pela rede...");

        // ...and return test data.
        return findProfile(profileEmail);
    }

    public List<String> requestProfileFriendsFromFacebook(String profileEmail, String contactType) {
        // Here would be a POST request to one of the Facebook API endpoints.
        // Instead, we emulates long network connection, which you would expect
        // in the real life...
        simulateNetworkLatency();
        System.out.println("Facebook: Carregando '" + contactType + "' lista de '" + profileEmail + "' pela rede...");

        // ...and return test data.
        Profile profile = findProfile(profileEmail);
        if (profile != null) {
            return profile.getContacts(contactType);
        }
        return null;
    }

    private Profile findProfile(String profileEmail) {
        for (Profile profile : profiles) {
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

    /**
     * Código de criação do iterador para amigos.
     *
     * @param profileEmail
     * @return
     */
    @Override
    public ProfileIterator createFriendsIterator(String profileEmail) {
        return new FacebookIterator(this, "friends", profileEmail);
    }

    /**
     * Código de criação do iterador para colegas de trabalho.
     *
     * @param profileEmail
     * @return
     */
    @Override
    public ProfileIterator createCoworkersIterator(String profileEmail) {
        return new FacebookIterator(this, "coworkers", profileEmail);
    }

}

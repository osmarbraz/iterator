package com.iterator.iterators;

import com.iterator.socialnetworks.Facebook;
import com.iterator.profile.Profile;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa iteração sobre perfis do Facebook.
 */
public class FacebookIterator implements ProfileIterator {

    // O iterador precisa de uma referência para a coleção que ele percorre.
    private Facebook facebook;
    private String type;
    private String email;

    // Um objeto iterador percorre a coleção independentemente
    // de outros iteradores. Portanto ele tem que armazenar o
    // estado de iteração.
    private int currentPosition = 0;
    private List<String> emails = new ArrayList<>();
    private List<Profile> profiles = new ArrayList<>();

    public FacebookIterator(Facebook facebook, String type, String email) {
        this.facebook = facebook;
        this.type = type;
        this.email = email;
    }

    private void lazyLoad() {
        if (emails.size() == 0) {
            List<String> profiles = facebook.requestProfileFriendsFromFacebook(this.email, this.type);
            for (String profile : profiles) {
                this.emails.add(profile);
                this.profiles.add(null);
            }
        }
    }

    @Override
    public boolean hasNext() {
        lazyLoad();
        return currentPosition < emails.size();
    }

    /**
     * Cada classe iterador concreta tem sua própria implementação da interface
     * comum do iterador.
     *
     * @return
     */
    @Override
    public Profile getNext() {
        if (!hasNext()) {
            return null;
        }

        String friendEmail = emails.get(currentPosition);
        Profile friendProfile = profiles.get(currentPosition);
        if (friendProfile == null) {
            friendProfile = facebook.requestProfileFromFacebook(friendEmail);
            profiles.set(currentPosition, friendProfile);
        }
        currentPosition++;
        return friendProfile;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}

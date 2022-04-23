package com.iterator.iterators;

import com.iterator.socialnetworks.LinkedIn;
import com.iterator.profile.Profile;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa iteração sobre perfis do LinkedIn.
 */
public class LinkedInIterator implements ProfileIterator {

    // O iterador precisa de uma referência para a coleção que ele percorre.
    private LinkedIn linkedIn;
    private String type;
    private String email;

    // Um objeto iterador percorre a coleção independentemente
    // de outros iteradores. Portanto ele tem que armazenar o
    // estado de iteração.
    private int currentPosition = 0;
    private List<String> emails = new ArrayList<>();
    private List<Profile> contacts = new ArrayList<>();

    public LinkedInIterator(LinkedIn linkedIn, String type, String email) {
        this.linkedIn = linkedIn;
        this.type = type;
        this.email = email;
    }

    private void lazyLoad() {
        if (emails.size() == 0) {
            List<String> profiles = linkedIn.requestRelatedContactsFromLinkedInAPI(this.email, this.type);
            for (String profile : profiles) {
                this.emails.add(profile);
                this.contacts.add(null);
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
        Profile friendContact = contacts.get(currentPosition);
        if (friendContact == null) {
            friendContact = linkedIn.requestContactInfoFromLinkedInAPI(friendEmail);
            contacts.set(currentPosition, friendContact);
        }
        currentPosition++;
        return friendContact;
    }

    @Override
    public void reset() {
        currentPosition = 0;
    }
}

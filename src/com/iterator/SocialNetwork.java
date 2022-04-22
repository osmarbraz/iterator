package com.iterator;

/**
 * Define interface comum de rede social.
 *
 * A interface da coleção deve declarar um método fábrica para produzir
 * iteradores. Você pode declarar vários métodos se há diferentes tipos de
 * iteração disponíveis em seu programa.
 */
public interface SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);
}


import com.iterator.socialnetworks.Facebook;
import com.iterator.socialnetworks.LinkedIn;
import com.iterator.profile.Profile;
import com.iterator.socialnetworks.SocialNetwork;
import com.iterator.spammer.SocialSpammer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Especifique a rede social para direcionar a ferramenta de spam (padrão: Facebook):");
        System.out.println("1. Facebook");
        System.out.println("2. LinkedIn");
        String choice = scanner.nextLine();

        //Cria lista de tipos específicos.
        SocialNetwork network;
        if (choice.equals("2")) {
            network = new LinkedIn(createTestProfiles());
        } else {
            network = new Facebook(createTestProfiles());
        }

        // Envia uma mensagem
        SocialSpammer spammer = new SocialSpammer(network);
        spammer.sendSpamToFriends("anna.smith@bing.com", "Ei! Este é o amigo de Anna, Josh. Você pode me fazer um favor e curtir este post [link]?");
        spammer.sendSpamToCoworkers("anna.smith@bing.com", "Ei! Este é o chefe de Anna, Jason. Anna me disse que você estaria interessado em [link].");
    }

    /**
     * Cria uma lista de perfis sociais para a demonstração.
     *
     * @return Uma lista de perfis sociais preenchida.
     */
    public static List<Profile> createTestProfiles() {
        List<Profile> data = new ArrayList<Profile>();
        data.add(new Profile("anna.smith@bing.com", "Anna Smith", "friends:mad_max@ya.com", "friends:catwoman@yahoo.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("mad_max@ya.com", "Maximilian", "friends:anna.smith@bing.com", "coworkers:sam@amazon.com"));
        data.add(new Profile("bill@microsoft.eu", "Billie", "coworkers:avanger@ukr.net"));
        data.add(new Profile("avanger@ukr.net", "John Day", "coworkers:bill@microsoft.eu"));
        data.add(new Profile("sam@amazon.com", "Sam Kitting", "coworkers:anna.smith@bing.com", "coworkers:mad_max@ya.com", "friends:catwoman@yahoo.com"));
        data.add(new Profile("catwoman@yahoo.com", "Liza", "friends:anna.smith@bing.com", "friends:sam@amazon.com"));
        return data;
    }
}

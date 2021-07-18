import java.net.URI;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        URI web = new URI(
                "https",
                "en.wikipedia.org",
                "/wiki/URI_normalization",
                "Normalization_Process");

        getURI(web);
    }

    static void getURI(URI uri) {
        System.out.println(uri.getAuthority());
        System.out.println(uri.getPath());
        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getScheme());
        System.out.println(uri.getQuery());
    }
}

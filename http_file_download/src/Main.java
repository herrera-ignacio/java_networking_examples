import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String fileURL = "http://wallpaperswide.com/download/undercover_cat-wallpaper-3840x2400.jpg";
        String saveDir = "C:\\Users\\ignac\\Downloads";

        try {
            HttpDownloader.downloadFile(fileURL, saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

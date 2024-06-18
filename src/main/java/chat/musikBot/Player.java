package org.example;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Player extends Application {

    private static Player instance;
    private static String videoId;
    private WebView webView;
    private static final String API_KEY = "AIzaSyAgrgNAvW88YLfdrQFfzYHo4o-1YhLEaZc";
    private Queue<String> videoQueue = new LinkedList<>();
    private String currentVideo;
    private boolean repeat = false;
    private boolean isPlaying;

    public Player()
    {
        instance = this;
        this.isPlaying = false;
    }

    // Gibt die Singleton-Instanz der Player-Klasse zurück
    public static Player getInstance()
    {
        if (instance == null)
        {
            instance = new Player();
        }
        return instance;
    }

    // Startet die JavaFX-Anwendung und lädt das YouTube-Video
    @Override
    public void start(Stage primaryStage) {
        webView = new WebView();
        webView.getEngine().load(getYoutubeEmbedLink(videoId));

        Scene scene = new Scene(webView, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Spielt das YouTube-Video ab, das durch die übergebene ID identifiziert wird
    public void play(String videoId)
    {
        if (isPlaying)
        {
            addToQueue(videoId);
            System.out.println("Das Video mit der ID " + videoId + " wurde zur Warteschlange hinzugefügt.");
        }
        else
        {
            this.videoId = videoId;
            if (webView == null)
            {
                launch();
            }
            else
            {
                webView.getEngine().load(getYoutubeEmbedLink(videoId));
            }
            this.isPlaying = true;
            System.out.println("Das Video mit der ID " + videoId + " wird abgespielt.");
        }
    }

    // Stoppt die Wiedergabe des aktuellen YouTube-Videos
    public void stop() {
        if (webView != null) {
            webView.getEngine().load(null);
        }
    }
    /*
    // Gibt die Details des YouTube-Videos aus, das durch die übergebene ID identifiziert wird
    public void printVideoDetails(String videoId)
    {
        try
        {
            YouTube youtubeService = getService();
            YouTube.Videos.List request = youtubeService.videos()
                    .list("snippet,contentDetails,statistics");
            VideoListResponse response = request.setId(videoId).setKey(API_KEY).execute();
            if (!response.getItems().isEmpty())
            {
                Video video = response.getItems().get(0);
                System.out.println("Title: " + video.getSnippet().getTitle());
                System.out.println("Description: " + video.getSnippet().getDescription());
                System.out.println("Duration: " + video.getContentDetails().getDuration());
                System.out.println("View count: " + video.getStatistics().getViewCount());
            } else
            {
                System.out.println("Keine Videos gefunden mit der bereitgestellten ID.");
            }
        } catch (GeneralSecurityException e) {
            System.out.println("Sicherheitsfehler beim Zugriff auf die YouTube API.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("E/A-Fehler beim Zugriff auf die YouTube API.");
            e.printStackTrace();
        }
    }
    */
    // Gibt den Einbettungslink für das YouTube-Video zurück, das durch die übergebene ID identifiziert wird
    private String getYoutubeEmbedLink(String videoId)
    {
        return "https://www.youtube.com/embed/" + videoId;
    }

    // Erstellt und gibt eine Instanz des YouTube-Dienstes zurück
    public static YouTube getService() throws GeneralSecurityException, IOException
    {
        return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("youtube-data-api")
                .build();
    }

    // Extrahiert und gibt die Video-ID aus dem übergebenen YouTube-Link zurück
    public static String extractVideoId(String videoLink)
    {
        String videoId = null;
        if (videoLink != null && videoLink.trim().length() > 0 && videoLink.startsWith("https://www.youtube.com/watch?v="))
        {
            videoId = videoLink.substring("https://www.youtube.com/watch?v=".length());
        }
        return videoId;
    }

    public boolean isPlaying()
    {
        return this.isPlaying;
    }

    public void clearQueue()
    {
        videoQueue.clear();
    }

    public void addToQueue(String videoLink)
    {
        videoQueue.add(videoLink);
    }

    public Queue<String> getVideoQueue()
    {
        return videoQueue;
    }

    public void skip()
    {
        if (!videoQueue.isEmpty())
        {
            play(videoQueue.poll());
        }
        else
        {
            System.out.println("Die Warteschlange ist leer, es gibt kein Video zum Überspringen.");
        }
    }

    public void shuffleQueue()
    {
        LinkedList<String> list = new LinkedList<>(videoQueue);
        Collections.shuffle(list);
        videoQueue = new LinkedList<>(list);
    }

    public void setRepeat(boolean repeat)
    {
        this.repeat = repeat;
    }

    public void next()
    {
        if (repeat && currentVideo != null)
        {
            play(currentVideo);
        }
        else
        {
            if (!videoQueue.isEmpty())
            {
                String nextVideo = videoQueue.poll();
                play(nextVideo);
            }
            else
            {
                System.out.println("Die Warteschlange ist leer, es gibt kein Video zum Abspielen.");
            }
        }
    }
}
package chat.musikBot;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class PlayCommand implements Command {
    private static final String API_KEY = "AIzaSyAgrgNAvW88YLfdrQFfzYHo4o-1YhLEaZc";

    @Override
    public void execute(String argument) {
        String videoLink = argument;
        String videoId = extractVideoId(videoLink);

        if (videoId == null) {
            System.out.println("Ungültiger Video Link.");
            return;
        }

        try {
            YouTube youtubeService = getService();
            YouTube.Videos.List request = youtubeService.videos()
                    .list("snippet,contentDetails,statistics");
            VideoListResponse response = request.setId(videoId).setKey(API_KEY).execute();
            if (!response.getItems().isEmpty()) {
                Video video = response.getItems().get(0);
                System.out.println("Title: " + video.getSnippet().getTitle());
                System.out.println("Description: " + video.getSnippet().getDescription());
                System.out.println("Duration: " + video.getContentDetails().getDuration());
                System.out.println("View count: " + video.getStatistics().getViewCount());
            } else {
                System.out.println("No videos found with the provided ID.");
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    public static YouTube getService() throws GeneralSecurityException, IOException {
        return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), null)
                .setApplicationName("youtube-data-api")
                .build();
    }

    private static String extractVideoId(String videoLink) {
        String videoId = null;
        if (videoLink != null && videoLink.trim().length() > 0 && videoLink.startsWith("https://www.youtube.com/watch?v=")) {
            videoId = videoLink.substring("https://www.youtube.com/watch?v=".length());
        }
        return videoId;
    }
}
package io.isolve.photobooth.api;

/**
 * Created by isuru on 4/10/16.
 */
public class PendingImage {

    private String imageUrl;

    public PendingImage() {
    }

    public PendingImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

package io.isolve.photobooth.api;

import java.util.List;

/**
 * Created by isuru on 4/10/16.
 */
public class PendingImages {
    private List<PendingImage> pendingImages;

    public PendingImages() {
    }

    public List<PendingImage> getPendingImages() {
        return pendingImages;
    }

    public void setPendingImages(List<PendingImage> pendingImages) {
        this.pendingImages = pendingImages;
    }
}

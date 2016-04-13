package io.isolve.photobooth.api;

/**
 * Created by isuru on 4/13/16.
 */
public class PrintJobStatus {
    private int completed;
    private int allSubmitted;
    private int error;

    public PrintJobStatus(int completed, int allSubmitted, int error) {
        this.completed = completed;
        this.allSubmitted = allSubmitted;
        this.error = error;
    }

    public PrintJobStatus() {
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getAllSubmitted() {
        return allSubmitted;
    }

    public void setAllSubmitted(int allSubmitted) {
        this.allSubmitted = allSubmitted;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}

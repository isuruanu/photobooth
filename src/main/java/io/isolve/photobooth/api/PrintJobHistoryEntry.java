package io.isolve.photobooth.api;

/**
 * Created by isuru on 4/13/16.
 */
public class PrintJobHistoryEntry {
    private PrintJobStatus printJobStatus;
    private String time;

    public PrintJobHistoryEntry(PrintJobStatus printJobStatus, String time) {
        this.printJobStatus = printJobStatus;
        this.time = time;
    }

    public PrintJobHistoryEntry() {
    }

    public PrintJobStatus getPrintJobStatus() {
        return printJobStatus;
    }

    public void setPrintJobStatus(PrintJobStatus printJobStatus) {
        this.printJobStatus = printJobStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

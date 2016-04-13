package io.isolve.photobooth.api;

import java.util.List;

/**
 * Created by isuru on 4/13/16.
 */
public class PrintJobHistory {
    private List<PrintJobHistoryEntry> printJobHistoryEntries;

    public List<PrintJobHistoryEntry> getPrintJobHistoryEntries() {
        return printJobHistoryEntries;
    }

    public void setPrintJobHistoryEntries(List<PrintJobHistoryEntry> printJobHistoryEntries) {
        this.printJobHistoryEntries = printJobHistoryEntries;
    }
}

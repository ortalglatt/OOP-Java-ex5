package filesprocessing.filters;

import java.io.File;

public interface Filter {

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     */
    boolean checkFile(File file);
}

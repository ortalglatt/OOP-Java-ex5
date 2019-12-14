package filesprocessing.filters;

import java.io.File;

public class AllFilter implements Filter {

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     * in that case - it will always return true.
     */
    public boolean checkFile(File file) {
        return true;
    }

}

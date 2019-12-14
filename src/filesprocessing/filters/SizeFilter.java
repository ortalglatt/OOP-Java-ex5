package filesprocessing.filters;

import java.io.File;

public abstract class SizeFilter implements Filter {

    protected static final double KB_FACTOR = 1024;

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     */
    public boolean checkFile(File file){
        long size = file.length();
        return this.checkSize(size);
    }

    /**
     * @param size size (in kilo-bites) to check.
     * @return true if the given size fits to the Class requirements, false otherwise.
     */
    abstract boolean checkSize(long size);

    /**
     * @return true if the given parameters are valid, false otherwise.
     */
    abstract boolean isValid();
}

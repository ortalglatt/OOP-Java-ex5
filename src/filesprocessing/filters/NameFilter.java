package filesprocessing.filters;

import java.io.File;

public abstract class NameFilter implements Filter{

    protected String valueToCheck;

    /**
     * Initialize a NameFilter object.
     * @param valueToCheck sub-string to check in the file name according to the class requirements.
     */
    NameFilter(String valueToCheck){
        this.valueToCheck = valueToCheck;
    }

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     */
    public boolean checkFile(File file){
        String name = file.getName();
        return this.checkName(name);
    }

    /**
     * @param fileName filename to check.
     * @return true if the given filename fits to the Class requirements, false otherwise.
     */
    abstract boolean checkName(String fileName);
}

package filesprocessing.filters;

import java.io.File;

public class NotFilterDecorator implements Filter{

    private Filter filter;

    /**
     * Initialize a NOT decorator, create a "not" filter of the given filter.
     * @param filter the filter we want to convert to "not filter".
     */
    public NotFilterDecorator(Filter filter){
        this.filter = filter;
    }

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     */
    public boolean checkFile(File file){
        return !this.filter.checkFile(file);
    }

}

package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneException;

import java.io.File;

public class WritableFilter extends YesOrNoFilter {

    /**
     * Initialize a WritableFilter object.
     * @param yesOrNo YES or NO string that determines the requirements of the class.
     * @throws TypeOneException if the given parameter isn't valid.
     */
    public WritableFilter(String yesOrNo) throws TypeOneException{
        super(yesOrNo);
        if (!this.isValid()) throw new TypeOneException("");
    }

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter with YES parameter, and false otherwise.
     */
    protected boolean checkFileYes(File file){
        return (file.canWrite());
    }
}

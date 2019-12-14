package filesprocessing.filters;

import java.io.File;

public abstract class YesOrNoFilter implements Filter{

    private static final String YES = "YES";
    private static final String NO = "NO";
    protected String yesOrNo;

    /**
     * Initialize a YesOrNoFilter object.
     * @param yesOrNo YES or NO string that determines the requirements of the class.
     */
    public YesOrNoFilter(String yesOrNo){
        this.yesOrNo = yesOrNo;
    }

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter, and false otherwise.
     */
    public boolean checkFile(File file){
        boolean resultIfYes = this.checkFileYes(file);
        if (this.yesOrNo.equals(YES)) return resultIfYes;
        return !resultIfYes;
    }

    /**
     * @param file a File object we want to check.
     * @return true if the file passes the filter with YES parameter, and false otherwise.
     */
    abstract boolean checkFileYes(File file);

    /**
     * @return true if the given parameters are valid, false otherwise.
     */
    protected boolean isValid(){
        return (this.yesOrNo.equals(YES) || this.yesOrNo.equals(NO));
    }
}

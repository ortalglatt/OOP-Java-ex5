package filesprocessing.orders;

import java.io.File;

public class TypeOrder implements Order{

    private static final String PERIOD = ".";
    private static TypeOrder singleInstance = null;

    /**
     * Initialize a SingleTone class of SizeOrder.
     */
    private TypeOrder(){}

    /**
     * Compare between the two given files according to there type.
     * if they have the same type, it will compare according to there absolute path.
     * @param file1 the first file to compare.
     * @param file2 the second file to compare.
     * @return -1 if the first file comes before the second, 1 if the second comes before the first.
     */
    public int checkOrder(File file1, File file2){
        int res = this.getType(file1).compareTo(this.getType(file2));
        if (res == 0) res = AbsOrder.getInstance().checkOrder(file1, file2);
        return res;
    }

    /**
     * @param file the file we want to find its type.
     * @return the type of the file (according to its suffix after the period).
     */
    private String getType(File file){
        String name = file.getName();
        int i = name.lastIndexOf(PERIOD);
        if (i == 0 || i == -1 || i == name.length()) return "";
        return name.substring(i + 1);
    }

    /**
     * @return the instance of TypeOrder.
     */
    public static Order getInstance() {
        if (singleInstance == null) singleInstance = new TypeOrder();
        return singleInstance;
    }
}

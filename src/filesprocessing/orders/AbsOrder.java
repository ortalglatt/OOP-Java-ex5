package filesprocessing.orders;

import java.io.File;

public class AbsOrder implements Order {

    private static AbsOrder singleInstance = null;

    /**
     * Initialize a SingleTone class of AbsOrder.
     */
    private AbsOrder(){}

    /**
     * Compare between the two given files according to there absolute path.
     * @param file1 the first file to compare.
     * @param file2 the second file to compare.
     * @return -1 if the first file comes before the second, 1 if the second comes before the first.
     */
    public int checkOrder(File file1, File file2){
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }

    /**
     * @return the instance of AbsOrder.
     */
    public static Order getInstance() {
        if (singleInstance == null) singleInstance = new AbsOrder();
        return singleInstance;
    }
}

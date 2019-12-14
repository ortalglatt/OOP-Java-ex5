package filesprocessing.orders;

import java.io.File;

public class SizeOrder implements Order{

    private static final double KB_FACTOR =  1024;
    private static SizeOrder singleInstance = null;

    /**
     * Initialize a SingleTone class of SizeOrder.
     */
    private SizeOrder(){}

    /**
     * Compare between the two given files according to there size (from the smaller to big).
     * if they have the same size, it will compare according to there absolute path.
     * @param file1 the first file to compare.
     * @param file2 the second file to compare.
     * @return -1 if the first file comes before the second, 1 if the second comes before the first.
     */
    public int checkOrder(File file1, File file2){
        Double size1 = file1.length() / KB_FACTOR;
        Double size2 = file2.length() / KB_FACTOR;
        int res = size1.compareTo(size2);
        if (res == 0) res = AbsOrder.getInstance().checkOrder(file1, file2);
        return res;
    }

    /**
     * @return the instance of SizeOrder.
     */
    public static Order getInstance() {
        if (singleInstance == null) singleInstance = new SizeOrder();
        return singleInstance;
    }
}

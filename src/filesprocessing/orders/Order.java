package filesprocessing.orders;

import java.io.File;

public interface Order {

    /**
     * Compare between the two given files according to the order class requirements.
     * @param file1 the first file to compare.
     * @param file2 the second file to compare.
     * @return -1 if the first file comes before the second, 1 if the second comes before the first.
     */
    int checkOrder(File file1, File file2);

}

package filesprocessing.orders;

import java.io.File;

public class ReverseOrderDecorator implements Order{

    private Order order;

    /**
     * Initialize a REVERSE decorator, create a "reverse order" of the given order.
     * @param order the order we want to convert to "reverse order".
     */
    public ReverseOrderDecorator(Order order){
        this.order = order;
    }

    /**
     * Compare between the two given files according to the order class requirements.
     * @param file1 the first file to compare.
     * @param file2 the second file to compare.
     * @return -1 if the first file comes before the second, 1 if the second comes before the first.
     */
    public int checkOrder(File file1, File file2) {
        return this.order.checkOrder(file2, file1);
    }
}

package filesprocessing;

import filesprocessing.filters.*;
import filesprocessing.orders.*;

import java.io.File;
import java.util.ArrayList;

public class Section {

    private static final String WARNING_MSG = "Warning in line %s";
    private Filter filter;
    private Order order;
    private String filterString;
    private String orderString;
    private int filterIndex;
    private int orderIndex;

    /**
     * Initialize a Section object.
     * @param filterString a filter that this section need to be filtered by.
     * @param orderString an order that this section need to be sorted by.
     * @param filterIndex the line number of the filter in the commands file.
     * @param orderIndex  the line number of the order in the commands file.
     */
    Section(String filterString, String orderString, int filterIndex, int orderIndex) {
        this.filterString = filterString;
        this.orderString = orderString;
        this.filterIndex = filterIndex;
        this.orderIndex = orderIndex;
    }

    /**
     * Creates the needed Filter and Order objects of the class.
     * It will print a warning if their was a type 1 error in the filter or order creation.
     */
    public void initialize() {
        this.filter = FilterFactory.createFilter(this.filterString);
        if (this.filter == null) {
            System.err.println(String.format(WARNING_MSG, this.filterIndex));
            this.filter = new AllFilter();
        }

            this.order = OrderFactory.createOrder(this.orderString);
            if (this.order == null) {
                System.err.println(String.format(WARNING_MSG, this.orderIndex));
                this.order = AbsOrder.getInstance();
        }
    }

    /**
     * Does the main process of one section, filter all the files by the section filter, and than sort the
     * files due to the section order.
     * @param filesArr an array of all the files in the directory (excluding sub-dirs).
     */
    public void process(ArrayList<File> filesArr) {
        ArrayList<File> filteredFiles = new ArrayList<>();
        for (File file : filesArr) {
            if (this.filter.checkFile(file)) filteredFiles.add(file);
        }
        sort(filteredFiles, 0, filteredFiles.size() - 1);
        for (File file : filteredFiles) System.out.println(file.getName());
    }

    /**
     * Sort (quickSort) the given files array, due to the section order object.
     * @param filesArr files array to sort.
     * @param low the lower index to work on (at the first call, it will be = 0).
     * @param high the higher index to work on (at the first call, it will be = filesArr.length - 1).
     */
    private void sort(ArrayList<File> filesArr, int low, int high) {
        if (low < high) {
            int p = partition(filesArr, low, high);
            sort(filesArr, low, p - 1);
            sort(filesArr, p + 1, high);
        }
    }

    /**
     * Choose a pivot, and order the array such that all the files before the pivot arr "smaller" than him,
     * and all the files after the pivot are "greater" than him.
     * ("smaller" and "greater" are due to the order object.)
     * @param filesArr files array to work on.
     * @param low      the lower index to work on
     * @param high     the higher index to work on.
     * @return the pivot index.
     */
    private int partition(ArrayList<File> filesArr, int low, int high) {
        File pivotFile = filesArr.get(high);
        int i = low - 1; // index of smaller element
        for (int j = low; j < high; j++) {
            File curFile = filesArr.get(j);
            int res = order.checkOrder(curFile, pivotFile);
            if (res < 0) {
                i++;
                // swap filesArr[i] and filesArr[j]
                filesArr.set(j, filesArr.get(i));
                filesArr.set(i, curFile);
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        File temp = filesArr.get(i + 1);
        filesArr.set(i + 1, filesArr.get(high));
        filesArr.set(high, temp);
        return i + 1;
    }

}

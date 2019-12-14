package filesprocessing;

import filesprocessing.exceptions.TypeOneException;
import filesprocessing.filters.*;

public class FilterFactory {
    private static final String SEP = "#";
    private static final String GREATER = "greater_than";
    private static final String BETWEEN = "between";
    private static final String SMALLER = "smaller_than";
    private static final String FILE = "file";
    private static final String CONTAINS = "contains";
    private static final String PREFIX = "prefix";
    private static final String SUFFIX = "suffix";
    private static final String WRITABLE = "writable";
    private static final String EXECUTABLE = "executable";
    private static final String HIDDEN = "hidden";
    private static final String ALL = "all";
    private static final String NOT = "NOT";


    /**
     * Creates a single filter that describes in the given String array.
     * @param filterString a string that describes the needed filter.
     * @return a filter object as described. If their was a problem with the description, it will return null.
     */
    public static Filter createFilter(String filterString) {
        String[] args = filterString.split(SEP);
        Filter filterToReturn;
        int notIndex = 2;
        try {
            switch (args[0]) {
                case GREATER:
                    filterToReturn = new GreaterFilter(Double.valueOf(args[1]));
                    break;
                case BETWEEN:
                    filterToReturn = new BetweenFilter(Double.valueOf(args[1]), Double.valueOf(args[2]));
                    notIndex = 3;
                    break;
                case SMALLER:
                    filterToReturn = new SmallerFilter(Double.valueOf(args[1]));
                    break;
                case FILE:
                    filterToReturn = new FileFilter(args[1]);
                    break;
                case CONTAINS:
                    filterToReturn = new ContainsFilter(args[1]);
                    break;
                case PREFIX:
                    filterToReturn = new PrefixFilter(args[1]);
                    break;
                case SUFFIX:
                    filterToReturn = new SuffixFilter(args[1]);
                    break;
                case WRITABLE:
                    filterToReturn = new WritableFilter(args[1]);
                    break;
                case EXECUTABLE:
                    filterToReturn = new ExecutableFilter(args[1]);
                    break;
                case HIDDEN:
                    filterToReturn = new HiddenFilter(args[1]);
                    break;
                case ALL:
                    filterToReturn = new AllFilter();
                    notIndex = 1;
                    break;
                default:
                    return null;
            }
        } catch (TypeOneException | IndexOutOfBoundsException e) {return null;}
        if (args.length >= notIndex + 1) {
            if (args[notIndex].equals(NOT)) return new NotFilterDecorator(filterToReturn);
            return null;
        }
        return filterToReturn;
    }

}

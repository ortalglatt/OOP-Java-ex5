package filesprocessing.filters;

public class SuffixFilter extends NameFilter {

    /**
     * Initialize a SuffixFilter object.
     * @param valueToCheck sub-string to check if the filename suffix is equal to.
     */
    public SuffixFilter(String valueToCheck){
        super(valueToCheck);
    }

    /**
     * @param name name to check.
     * @return true if the given name's suffix is equal to the value of the class, false otherwise.
     */
    protected boolean checkName(String name){
        return (name.endsWith(this.valueToCheck));
    }
}
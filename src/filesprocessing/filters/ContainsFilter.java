package filesprocessing.filters;

public class ContainsFilter extends NameFilter {

    /**
     * Initialize a ContainsFilter object.
     * @param valueToCheck sub-string to check if its contained in the filename.
     */
    public ContainsFilter(String valueToCheck){
        super(valueToCheck);
    }

    /**
     * @param name name to check.
     * @return true if the given name contains the value of the class, false otherwise.
     */
    protected boolean checkName(String name){
        return (name.contains(this.valueToCheck));
    }
}
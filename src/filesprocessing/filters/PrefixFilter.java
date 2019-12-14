package filesprocessing.filters;

public class PrefixFilter extends NameFilter {

    /**
     * Initialize a PrefixFilter object.
     * @param valueToCheck sub-string to check if the filename prefix is equal to.
     */
    public PrefixFilter(String valueToCheck){
        super(valueToCheck);
    }

    /**
     * @param name name to check.
     * @return true if the given name's prefix is equal to the value of the class, false otherwise.
     */
    protected boolean checkName(String name){
        return (name.startsWith(this.valueToCheck));
    }
}
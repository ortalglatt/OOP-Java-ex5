package filesprocessing.filters;

public class FileFilter extends NameFilter {

    /**
     * Initialize a FileFilter object.
     * @param valueToCheck sub-string to check if the filename is equal to.
     */
    public FileFilter(String valueToCheck){
        super(valueToCheck);
    }

    /**
     * @param name name to check.
     * @return true if the given name equals to the value of the class, false otherwise.
     */
    protected boolean checkName(String name){
        return (name.equals(this.valueToCheck));
    }
}

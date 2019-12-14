package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneException;

public class GreaterFilter extends SizeFilter {

    private double lowerBound;

    /**
     * Initialise a GreaterFilter object.
     * @param lowerBound the lower bound of the files size.
     * @throws TypeOneException if the parameters aren't valid.
     */
    public GreaterFilter(double lowerBound) throws TypeOneException{
        this.lowerBound = lowerBound * KB_FACTOR;
        if (!this.isValid()) throw new TypeOneException("");
    }

    /**
     * @param size size (in kilo-bites) to check.
     * @return true if the given size is bigger than the lowerBound, false otherwise.
     */
    protected boolean checkSize(long size){
        return (size > this.lowerBound);
    }

    /**
     * @return true if the given parameters are valid, false otherwise.
     */
     public boolean isValid(){
        return (this.lowerBound >= 0);
    }
}

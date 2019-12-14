package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneException;

public class SmallerFilter extends SizeFilter {

    private double upperBound;

    /**
     * Initialize a SmallerFilter object.
     * @param upperBound the upper bound of the files size.
     * @throws TypeOneException if the parameters aren't valid.
     */
    public SmallerFilter(double upperBound) throws TypeOneException{
        this.upperBound = upperBound * KB_FACTOR;
        if (!this.isValid()) throw new TypeOneException("");
    }

    /**
     * @param size size (in kilo-bites) to check.
     * @return true if the given size is smaller than the lowerBound, false otherwise.
     */
    protected boolean checkSize(long size){
        return (size < this.upperBound);
    }

    /**
     * @return true if the given parameters are valid, false otherwise.
     */
    public boolean isValid(){
        return (this.upperBound >= 0);
    }
}
package filesprocessing.filters;

import filesprocessing.exceptions.TypeOneException;

public class BetweenFilter extends SizeFilter {

    private double lowerBound;
    private double upperBound;

    /**
     * Initialize a BetweenFilter object.
     * @param lowerBound the lower bound of the files size.
     * @param upperBound the upper bound of the files size.
     * @throws TypeOneException if the parameters aren't valid.
     */
    public BetweenFilter(double lowerBound, double upperBound) throws TypeOneException{
        this.lowerBound = lowerBound * KB_FACTOR;
        this.upperBound = upperBound * KB_FACTOR;
        if (!this.isValid()) throw new TypeOneException("");
    }

    /**
     * @param size size (in kilo-bites) to check.
     * @return true if the given size is between the bounds or equal to them , false otherwise.
     */
    protected boolean checkSize(long size){
        return (size >= this.lowerBound && size <= this.upperBound);
    }

    /**
     * @return true if the given parameters are valid, false otherwise.
     */
    public boolean isValid(){
        return (this.lowerBound >= 0 && this.upperBound >= 0 && this.lowerBound < this.upperBound);
    }
}

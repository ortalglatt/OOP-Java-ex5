package filesprocessing.exceptions;

public  class TypeOneException extends Exception {

    /**
     * Initialize a type 1 exception with the given massage.
     * @param msg a massage about the problem.
     */
    public TypeOneException(String msg){
        super(msg);
    }
}
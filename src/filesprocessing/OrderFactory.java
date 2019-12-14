package filesprocessing;

import filesprocessing.orders.*;

public class OrderFactory {
    private static final String SEP = "#";
    private static final String ABS = "abs";
    private static final String TYPE = "type";
    private static final String SIZE = "size";
    private static final String REVERSE = "REVERSE";

    /**
     * Creates a single order that describes in the given String array.
     * @param orderString a string that describes the needed order.
     * @return an order object as described. If their was a problem with the description, it will return null.
     */
    public static Order createOrder(String orderString){
        String[] args = orderString.split(SEP);
        Order orderToReturn;
        switch (args[0]) {
            case ABS:
                orderToReturn = AbsOrder.getInstance();
                break;
            case TYPE:
                orderToReturn = TypeOrder.getInstance();
                break;
            case SIZE:
                orderToReturn = SizeOrder.getInstance();
                break;
            default:
                return null;
        }
        if (args.length == 2) {
            if (args[1].equals(REVERSE)) return new ReverseOrderDecorator(orderToReturn);
            return null;
        }
        return orderToReturn;
    }

}

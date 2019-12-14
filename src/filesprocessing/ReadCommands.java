package filesprocessing;

import filesprocessing.exceptions.TypeTwoException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCommands {
    private static final String FILTER = "FILTER";
    private static final String ORDER = "ORDER";
    private static final String DEFAULT_ORDER = "abs";
    private static final String NO_FILTER_MSG = "ERROR: filter sub-section is missing";
    private static final String NO_ORDER_MSG = "ERROR: order sub-section is missing";
    private static final String BAD_NAME_MSG = "ERROR: invalid sub-section name";
    private static final String IO_ERR_MSG = "ERROR: I/O error in the command file";

    private int curLineIndex = 0;
    private BufferedReader commandReader;
    private ArrayList<Section> sections = new ArrayList<>();

    /**
     * Initialize a ReadCommands object.
     * @param reader the reader of the commands file.
     */
    public ReadCommands(BufferedReader reader){
        this.commandReader = reader;
    }

    /**
     * Reads one line from the commands file with the commandReader, and forwarding (+1) the curLineIndex
     * (index counter).
     * @return the current line.
     * @throws TypeTwoException If their was an I/O problem.
     */
    private String readLine() throws TypeTwoException{
        try {
            this.curLineIndex++;
            return this.commandReader.readLine();
        }
        catch (IOException e) {
            throw new TypeTwoException(IO_ERR_MSG);
        }
    }

    /**
     * Reads all the commands from the commands file. for each section (filter and order), it will create a
     * new Section object, and add it to the sections array.
     * @throws TypeTwoException If their was a type 2 error in the FILTER/ORDER titles or in the reading
     * process.
     */
    public ArrayList<Section> readCommandsFile() throws TypeTwoException{
        String curLine = this.readLine();
        String filterString;
        String orderString;
        int filterIndex;
        int orderIndex;

        try {
            while (curLine != null) {
                this.checkFilterTitle(curLine);

                filterString = this.readLine();
                filterIndex = this.curLineIndex;

                this.checkOrderTitle(this.readLine());

                curLine = this.readLine();
                orderIndex = this.curLineIndex;
                if (curLine == null || curLine.equals(FILTER)) {
                    orderString = DEFAULT_ORDER;
                } else {
                    orderString = curLine;
                    curLine = this.readLine();
                }
                this.sections.add(
                        new Section(filterString, orderString, filterIndex, orderIndex));
            }
        }
        catch (TypeTwoException e){
            throw new TypeTwoException(e.getMessage());
        }
        return this.sections;
    }

    /**
     * Checks the string that supposed to be the FILTER title.
     * @param title the title appears in the commands file.
     * @throws TypeTwoException If their was a problem in the title.
     */
    private void checkFilterTitle(String title) throws TypeTwoException{
        if (title.equals(ORDER)) throw new TypeTwoException(NO_FILTER_MSG);
        else if (!title.equals(FILTER)) throw new TypeTwoException(BAD_NAME_MSG);
    }

    /**
     * Checks the string that supposed to be the ORDER title.
     * @param title the title appears in the commands file.
     * @throws TypeTwoException If their was a problem in the title.
     */
    private void checkOrderTitle(String title) throws TypeTwoException {
        if (title == null || title.equals(FILTER)) throw new TypeTwoException(NO_ORDER_MSG);
        else if (!title.equals(ORDER)) throw new TypeTwoException(BAD_NAME_MSG);
    }
}

package filesprocessing;

import filesprocessing.exceptions.TypeTwoException;

import java.io.*;
import java.util.ArrayList;

public class DirectoryProcessor {


    private static final String ARGS_ERR_MSG = "ERROR: invalid arguments";
    private static final String IO_ERR_MSG = "ERROR: I/O error in the command file";

    private File sourceDir;
    private File commandFile;
    private BufferedReader commandReader;

    /**
     * Initialize a DirectoryProcessor object.
     * @param sourceDirPath path to the source directory.
     * @param commandFilePath Path to the commands file.
     * @throws TypeTwoException If their is a problem with the directory or with the file.
     */
    DirectoryProcessor(String sourceDirPath, String commandFilePath) throws TypeTwoException {
        try {
            this.sourceDir = new File(sourceDirPath);
            this.commandFile = new File(commandFilePath);
            if (!this.sourceDir.isDirectory() || !this.commandFile.isFile())
                throw new TypeTwoException(ARGS_ERR_MSG);
            this.commandReader = new BufferedReader(new FileReader(this.commandFile));
        }
        catch (IOException | NullPointerException e){
            throw new TypeTwoException(IO_ERR_MSG);
        }
    }

    /**
     * Creates all the needed filters and orders objects, and does the one section process for each section in
     * the commands file.
     */
    private void filesProcess(ArrayList<Section> sections) {
        ArrayList<File> files = new ArrayList<>();
        for (File file : this.sourceDir.listFiles()) {
            if (file.isFile()) files.add(file);
        }
        for (Section section : sections) {
            section.initialize();
            section.process(files);
        }
    }

    /**
     * Runs the whole directory process.
     * @param args the arguments - will be legal if the first argument is a path to the source directory, and
     *             the second argument is the path to the commands file.
     */
    public static void main(String[] args){
        if (args.length != 2) {
            System.err.println(ARGS_ERR_MSG);
            return;
        }
        try {
            DirectoryProcessor dirProc = new DirectoryProcessor(args[0], args[1]);
            ReadCommands commandsFileReader = new ReadCommands(dirProc.commandReader);
            ArrayList<Section> sections = commandsFileReader.readCommandsFile();
            dirProc.filesProcess(sections);
        }
        catch (TypeTwoException e){
            System.err.println(e.getMessage());
        }
    }

}

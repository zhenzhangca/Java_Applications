package ca.jrvs.apps.grep;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestGrepApp {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE:regex rootPath outFile");
        }
        //print regex
        //System.out.println(args[0]);
        //print rootPath
        // System.out.println(args[1]);
        //print outFile
        //System.out.println(args[2]);
        JavaGrep jg = new JavaGrepImp();
        jg.setRegex(args[0]);
        jg.setRootPath(args[1]);
        jg.setOutFile(args[2]);

        try {
            jg.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //read the lines of /temp/grep.out
        //File read = new File(args[2]);
        //List<String> readLines = jg.readLines(read);
        //System.out.println(readLines);
    }
}

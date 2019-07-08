package ca.jrvs.apps.grep;

import java.io.IOException;

public class JavaGrepApp {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("USAGE:regex rootPath outFile");
        }

        JavaGrep jg = new JavaGrepImp();
        jg.setRegex(args[0]);
        jg.setRootPath(args[1]);
        jg.setOutFile(args[2]);

        try {
            jg.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

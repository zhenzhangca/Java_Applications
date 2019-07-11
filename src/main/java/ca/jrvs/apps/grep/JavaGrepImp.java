package ca.jrvs.apps.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepImp implements JavaGrep {

    private String regex;
    private String rootPath;
    private String outFile;

    private List<File> fileList = new ArrayList<>();

    @Override
    public void process() throws IOException {
        List<String> matchedLines = new ArrayList<>();
        List<File> fileList = this.listFiles(rootPath);
        for (File file : fileList) {
            List<String> lines = this.readLines(file);
            for (String line : lines) {
                if (this.containsPattern(line)) {
                    matchedLines.add(line);
                }
            }
        }
        this.writeToFile(matchedLines);
    }

    @Override
    public List<File> listFiles(String rootDir) {
        // Get the directory object in rootDir
        File dir = new File(rootDir);
        // Get all the files or directories in the dir
        File[] files = dir.listFiles();
        // Check if dir is empty
        if (files == null) {
            return null;
        }
        // Traverse all the files in rootDir including sub_directory
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                fileList.add(files[i]);
            } else if (files[i].isDirectory()) {
                // Recursion
                this.listFiles(files[i].getAbsolutePath());
            }
        }
        return fileList;
    }

    @Override
    public List<String> readLines(File inputFile) {
        List<String> lines = new ArrayList<>();
        BufferedReader in;
        String line;
        try {
            in = new BufferedReader(new FileReader(inputFile));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    @Override
    public boolean containsPattern(String line) {
        return line.matches(regex);
    }

    @Override
    public void writeToFile(List<String> lines) throws IOException {
        // Get outputFile path
        File outputFile = new File(outFile);
        // Create outputFile according to the path(in /private/tem)
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        BufferedWriter out;

        try {
            out = new BufferedWriter(new FileWriter(outputFile));
            for (String line : lines) {
                // Break the lines
                out.write(line + "\n");
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getRootPath() {
        return this.rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String getOutFile() {
        return this.outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }
}

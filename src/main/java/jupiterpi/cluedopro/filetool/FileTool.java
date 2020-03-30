package jupiterpi.cluedopro.filetool;

import java.util.*;
import java.io.*;

public class FileTool {
    private String fileName;
    private ArrayList<String> lines = new ArrayList();

    public FileTool(String fileName) {
        this.fileName = fileName;

        try {
            BufferedReader Reader = new BufferedReader(new FileReader(fileName));
            boolean fileEnd = false;

            while (!fileEnd) {
                String line = Reader.readLine();
                if (line == null) fileEnd = true;
                else lines.add(line);
            }
            Reader.close();
        } catch (IOException x) {
            System.err.println("Can not load file " + fileName + ". ");
        }
    }

    public FileTool(File file) {
		this.fileName = file.getName();

		try {
			BufferedReader Reader = new BufferedReader(new FileReader(file));
			boolean fileEnd = false;

			while (!fileEnd) {
				String line = Reader.readLine();
				if (line == null) fileEnd = true;
				else lines.add(line);
			}
			Reader.close();
		} catch (IOException x) {
			System.err.println("Can not load file " + fileName + ". ");
		}
	}

    public ArrayList<String> getFile() {
        return lines;
    }

    public String getFileForOutput() {
        String returning = "";
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                returning += lines.get(i) + "\n";
            } else {
                returning += lines.get(i);
            }
        }
        return returning;
    }

    public void setFile(ArrayList newFile) {
        this.lines = newFile;
    }

    public String getLine(int line) {
        return (String) lines.get(line);
    }

    public void setLine(int line, String text) {
        lines.set(line, text);
    }

    public void writeToLine(int line, String text) {
        this.setLine(line, this.getLine(line) + text);
    }

    public void saveFile() throws IOException {
        String input;
        BufferedWriter Writer = new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < lines.size(); i++) {
            Writer.write(lines.get(i) + "\r\n");
        }
        Writer.close();
    }
}
package input;

import labels.LabelService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public static List<String> formatInputFile() throws IOException {
        List<String> s = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            //change. because it creates 5+ new string
            String line = br.readLine();
            int lineNumber = 0;

            while (line != null) {

                line = formatLine(line);
                if (line == null) {
                    line = br.readLine();
                    continue;
                }


                var tokens = line.split(" ");
                String label = tokens[0];

                if (label.endsWith(":")){
                    LabelService.addLabel(label.substring(0,label.length()-1), lineNumber);

                    if (tokens.length > 1) {
                        line = line.split(": ", 2)[1];
                    } else {
                        line = br.readLine();
                        continue;
                    }


                }
                s.add(line);
                line = br.readLine();
                lineNumber++;
            }
        }
        return s;
    }

    private static String formatLine(String line){


        if (line.isBlank() || line.equals("//")) {
            return null;
        }
        line = line.split("//")[0];
        line = line.replaceAll(",", " ");
        line = line.strip();
        line = line.toLowerCase();
        while (line.contains("  ")) {
            line = line.replace("  ", " ");
        }
        if (line.isEmpty()) {
            return null;
        }
        return line;
    }

}

package input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public List<String> formatInputFile() throws IOException {
        List<String> s = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            //change. because it creates 5+ new string
            String line = br.readLine();
            while (line != null) {
                if (line.isBlank()) {
                    line = br.readLine();
                    continue;
                }
                line = line.split("//")[0];
                line = line.replaceAll(",", " ");
                line = line.strip();
                line = line.toLowerCase();
                while (line.contains("  ")) {
                    line = line.replace("  ", " ");
                }
                if (line.isEmpty()) {
                    line = br.readLine();
                    continue;
                }
                s.add(line);
                line = br.readLine();
            }
        }
        return s;
    }
}

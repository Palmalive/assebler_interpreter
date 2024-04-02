package labels;

import exceptions.LabelException;

import java.util.HashMap;
import java.util.Map;

public class LabelService {
    static Map<String, Integer> labels = new HashMap<>();

    public static void addLabel(String label, Integer lineNumber) {
        labels.put(label, lineNumber);
    }

    public static Integer getLabelAddress(String label) throws LabelException {
        if (labels.containsKey(label)){
            return labels.get(label);
        } else {
            throw new LabelException("There is no such label");
        }
    }
}

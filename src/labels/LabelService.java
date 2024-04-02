package labels;

import exceptions.FlagAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;

public class LabelService {
    static Map<String, String> labels = new HashMap<>();

    public static void addLabel(String label, String instruction) throws FlagAlreadyExistsException {
        if (labels.containsKey(label)){
            throw new FlagAlreadyExistsException("Flag "+ label + " already exists");
        }
        labels.put(label, instruction);
    }

    public static Map<String, String> getAllLabels(){
        return labels;
    }
}

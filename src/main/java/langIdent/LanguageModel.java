package langIdent;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains ngrams of a language and their frequencies.
 * Created by Mahdi on 7/13/2016.
 */
public class LanguageModel {
    private String name;
    private Map<String, Integer> ngrams;

    public LanguageModel() {
        ngrams = new HashMap<String, Integer>();
    }

    public LanguageModel(String name) {
        this();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public Map<String, Integer> getNgrams() {
        return ngrams;
    }
}

package langIdent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Converts a text document, an array of string lines, to ngrams and their frequencies
 * Created by Mahdi on 7/13/2016.
 */
public class NgramProcess {

    /***
     * Returns the ngrams of a text and their frequencies
     *
     * @param text
     * @param max_ngram_length
     * @return
     */
    public Map<String, Integer> getNgram(String text, int max_ngram_length) {
        return getNgram(text.split("\r\n|\n"), max_ngram_length);
    }

    public Map<String, Integer> getNgram(String[] text, int max_ngram_length) {
        Map<String, Integer> ngrams = new HashMap<String, Integer>();
        for (int i = 0; i < text.length; ++i) {
            for (String ngram : getNgramList(text[i], max_ngram_length)) {
                int freq = 1;
                if (ngrams.containsKey(ngram)) {
                    freq += ngrams.get(ngram);
                }
                ngrams.put(ngram, freq);
            }
        }
        return ngrams;
    }

    private List<String> getNgramList(String text, int max_ngram) {
        text = text.replace("\t", " ");
        List<String> ngramsList = new ArrayList<String>();
        for (int i = 1; i <= max_ngram; ++i) {
            for (int j = 0; j < text.length() - i + 1; ++j) {
                String ngram = text.substring(j, j + i);
                if (ngram.trim().length() > 0) {
                    ngramsList.add(ngram);
                }
            }
        }
        return ngramsList;
    }
}

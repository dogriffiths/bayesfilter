package uk.co.blackpepper.bayes;

import java.util.List;

/**
 * Takes a string and breaks it into words.
 *
 * Created by davidg on 13/04/2017.
 */
@FunctionalInterface
public interface TextParser {
    public List<String> words(String text);
}
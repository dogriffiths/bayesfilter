package uk.co.blackpepper.bayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * A {@link uk.co.blackpepper.bayes.SampleSource} created from a directory of files.
 * It will ignore sub-directories.
 *
 * Created by davidg on 12/04/2017.
 */
class DirectorySampleSource implements SampleSource {

    private final Concordance concordance;
    private final int count;

    public DirectorySampleSource(String dirName) throws IOException {
        this(new File(dirName));
    }

    public DirectorySampleSource(File dir) throws IOException {
        Concordance c = new Concordance("");
        int count = 0;

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                c = c.merge(new Concordance(readFileAsString(file)));
                count++;
            }
        }
        this.concordance = c;
        this.count = count;
    }

    @Override
    public int sampleCount() {
        return count;
    }

    @Override
    public Concordance concordance() {
        return concordance;
    }

    private static String readFileAsString(File filePath)
            throws IOException{
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}

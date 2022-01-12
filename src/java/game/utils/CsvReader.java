package game.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

//Classe pour gérer la lecture des fichiers csv
public class CsvReader {
    public List<List<String>> parseCsv(URI file) {
        List<List<String>> data = new ArrayList<>();
        try {
            InputStreamReader reader =  new InputStreamReader(file.toURL().openStream());
            BufferedReader br = new BufferedReader(reader);

            String line = br.readLine();
            while(line != null) {
                List<String> lineData = Arrays.asList(line.split(";"));
                data.add(lineData);
                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

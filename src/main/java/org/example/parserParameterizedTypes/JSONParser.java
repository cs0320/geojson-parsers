package org.example.parserParameterizedTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.example.parserParameterizedTypes.GeoMapCollection.GeoMapCollection;

public class JSONParser {
  private GeoMapCollection data;
  public JSONParser(String filePath) throws FileNotFoundException {
    try {
      // ***************** READING THE FILE *****************
      FileReader jsonReader = new FileReader(filePath);
      BufferedReader br = new BufferedReader(jsonReader);
      String fileString = "";
      String line = br.readLine();
      while (line != null) {
        fileString = fileString + line;
        line = br.readLine();
      }
      jsonReader.close();

      // ****************** CREATING THE ADAPTER ***********
      GeoMapAdapter myadapter = new GeoMapAdapter();
      this.data = myadapter.fromJson(fileString);

      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
  }

  public GeoMapCollection getData(){
    return this.data;
  }

}

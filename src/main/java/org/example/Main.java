package org.example;

import java.io.FileNotFoundException;
import org.example.parserParameterizedTypes.GeoMapCollection.GeoMapCollection;
import org.example.parserParameterizedTypes.JSONParser;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    JSONParser myDataSource = new JSONParser("data/geojson/fullDownload.geojson");
    GeoMapCollection geomapCollection = myDataSource.getData();
    System.out.println("Geomap parsed");
  }


}
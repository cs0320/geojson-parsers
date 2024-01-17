package org.example;

import java.io.FileNotFoundException;
import org.example.geoMapIntegration.GeoMapCollection.GeoMapCollection;
import org.example.geoMapIntegration.JSONParser;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {

    JSONParser myDataSource = new JSONParser("data/geojson/fullDownload.geojson");
    GeoMapCollection geomapCollection = myDataSource.getData();
    System.out.println("Geomap parsed");
    // **NOTE: there are a few geomap objects that aren't parsed because
    // there is no "geometry" object
  }
}
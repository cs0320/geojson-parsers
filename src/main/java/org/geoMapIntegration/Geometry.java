package edu.brown.cs32.main.datasource.geoMapIntegration;// package edu.brown.cs32.main.datasource.geoMapIntegration;
import java.util.List;
import java.util.ArrayList;

public class Geometry extends GeoMap{
//  public String geomtype;
  public List<List<List<List<Double>>>> coordinates; // float doesnt exist in fromJava

  public List<List<List<List<Double>>>> getCoordinates(){
    return this.coordinates;
  }
}

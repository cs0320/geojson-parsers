package edu.brown.cs32.main.datasource.geoMapIntegration;// package edu.brown.cs32.main.datasource.geoMapIntegration;


public class GeoMap {
  public String type;
  public Geometry geometry;
  public Property properties;

  public Geometry getGeometry(){
    return this.geometry;
  }

  public Property getProperty(){
    return this.properties;
  }
}

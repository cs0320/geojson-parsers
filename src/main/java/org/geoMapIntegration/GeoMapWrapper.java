package edu.brown.cs32.main.datasource.geoMapIntegration;
import java.util.HashSet;

public class GeoMapWrapper {
  private GeoMap geomap;
  private HashSet<String> keywords;
  public GeoMapWrapper(GeoMap geomap, HashSet<String> keywords){
    this.geomap = geomap;
    this.keywords = keywords;
  }

  public boolean hasKeyWord(String word){
    return this.keywords.contains(word);
  }

  public GeoMap getMap(){
    return this.geomap;
  }
}

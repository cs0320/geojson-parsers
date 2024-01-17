package org.example.geoMapIntegration.GeoMapCollection.GeoMap;

import java.util.List;
import org.example.geoMapIntegration.GeoMapCollection.GeoMap.GeoMap;

public class Geometry extends GeoMap {
  //  public String type;
  // NOTE: "type" does not work here because we used type already so I did not include a field
  public List<List<List<List<Double>>>> coordinates; // float doesnt exist in fromJava

  public List<List<List<List<Double>>>> getCoordinates(){
    return this.coordinates;
  }
}

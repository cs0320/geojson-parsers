package org.example.geoMapIntegration;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import org.example.geoMapIntegration.GeoMapCollection.GeoMap.GeoMap;
import org.example.geoMapIntegration.GeoMapCollection.GeoMap.Geometry;
import org.example.geoMapIntegration.GeoMapCollection.GeoMap.Property;
import org.example.geoMapIntegration.GeoMapCollection.GeoMapCollection;

public class GeoMapAdapter {

  private final Moshi moshi = new Moshi.Builder().build();
  Type geometry = Types.newParameterizedType(Geometry.class, String.class, List.class);
  Type geomaptype = Types.newParameterizedType(GeoMap.class, String.class, geometry, Property.class);
  Type type = Types.newParameterizedType(GeoMapCollection.class, String.class, List.class, geomaptype);
  @ToJson
  public String toJson(GeoMapCollection map) {
    JsonAdapter<GeoMapCollection> adapter = moshi.adapter(type);
    return adapter.toJson(map);
  }

  @FromJson
  public GeoMapCollection fromJson(String map) throws IOException {
    JsonAdapter<GeoMapCollection> adapter = moshi.adapter(type);
    return adapter.fromJson(map);
  }


}
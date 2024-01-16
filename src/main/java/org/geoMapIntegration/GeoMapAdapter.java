package edu.brown.cs32.main.datasource.geoMapIntegration;// package edu.brown.cs32.main.datasource.geoMapIntegration;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Properties;
import java.util.Set;
import java.util.List;

public class GeoMapAdapter {

  private final Moshi moshi = new Moshi.Builder().build();
  Type geometry = Types.newParameterizedType(Geometry.class, String.class, List.class);
  Type type = Types.newParameterizedType(GeoMap.class, String.class, geometry, Property.class);
  @ToJson
  public String toJson(GeoMap map) {
    JsonAdapter<GeoMap> adapter = moshi.adapter(type);
    return adapter.toJson(map);
  }

  @FromJson
  public GeoMap fromJson(String map) throws IOException {
    JsonAdapter<GeoMap> adapter = moshi.adapter(type);
    return adapter.fromJson(map);
  }


}
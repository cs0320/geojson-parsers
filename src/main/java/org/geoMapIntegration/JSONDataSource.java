package edu.brown.cs32.main.datasource.geoMapIntegration;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import edu.brown.cs32.main.datasource.geoMapIntegration.*;

public class JSONDataSource {
  private ArrayList<GeoMapWrapper> datawrappers = new ArrayList<>();
  private ArrayList<GeoMap> data = new ArrayList<>();
  public JSONDataSource(Boolean mocked, String filePath, Map<String, Double> dict) throws FileNotFoundException {
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

      // ***************** CLEANING THE JSON STRING *****************
      String useline = fileString;
      if (!mocked){
        useline = this.cleanGeoJsonString(fileString);
      }
      String[] splitLine = useline.split(",\\{\"type\":");
      for (int i = 0; i <  splitLine.length; i++){
        String jsonString = splitLine[i];
        if (i != 0){
          jsonString = "{\"type\":" + jsonString;
        }
        // ***************** STARTING MOSHI ON EACH GEOMAP *****************
        GeoMapAdapter myadapter = new GeoMapAdapter();
        GeoMap geo = myadapter.fromJson(jsonString);

        // ***************** FILTERING COORDINATES *****************
        if (geo.getGeometry() == null){
//          System.out.println("NOTE TO DEV: Did not add element: " + i + " b/c it does not have a geometry value");
        }
        else{
        List<List<Double>> coordinates = geo.getGeometry().getCoordinates().get(0).get(0);
        boolean outOfBounds = breaksCondition(coordinates, dict);
        if (!outOfBounds){
          // ***************** ADDING GEOMAP TO DATA *****************
          this.data.add(geo);
          GeoMapWrapper geowrapper = new GeoMapWrapper(geo, getGeoSet(geo));
          // ***************** CREATING A GEOMAP WRAPPER + ADDING TO DATA WRAPPERS *****************
          this.datawrappers.add(geowrapper);
        }}
      }

      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
  }

  /**
   * Creating a hashset of key words in area description --
   * design takes only words/numbers excluding punctuation
   * @param geomap
   * @return
   */
  private HashSet<String> getGeoSet(GeoMap geomap){
    HashSet<String> allTermsSet = new HashSet<>();
    Map<String, String> areaData = geomap.properties.area_description_data;
    for (String value: areaData.values()){
      String[] words = value.split("[^a-zA-Z0-9]+");
      allTermsSet.addAll(Arrays.asList(words));
    }
    return allTermsSet;
  }

  public ArrayList<GeoMap> getData(){
    ArrayList<GeoMap> dataCopy = new ArrayList<>(this.data);
    return dataCopy;
  }

  public Integer dataSize(){
    return this.data.size();
  }

  public Integer dataWrapperSize(){
    return this.datawrappers.size();
  }

  public ArrayList<GeoMap> searchByKeyWord(String word){
    ArrayList<GeoMap> mapsWithKeyWord = new ArrayList<>();
    for (int i=0; i<this.datawrappers.size(); i++) {
      GeoMapWrapper currWrapper = this.datawrappers.get(i);
      if (currWrapper.hasKeyWord(word)){
        mapsWithKeyWord.add(currWrapper.getMap());
      }
    }
    return mapsWithKeyWord;
  }

  /**
   * This method cleans the json string if it is the real geojson and not a mocked version
   * we remove the highest level hierarchy because it's not relevant to our geomap datastructure
   * @param fileString
   * @return
   */
  private String cleanGeoJsonString(String fileString){
    String lineWithoutType = fileString.replace("{\"type\": \"FeatureCollection\", \"features\": [", "");
    String lineWithoutClosing = lineWithoutType.replace("Fair\"}}}]}", "Fair\"}}}");
    return lineWithoutClosing;
  }

  /**
   * checks if the coordinates break the given condition if there is a requirement
   * @param coordinates represents list of coordinates in geomap
   * @param requiredCoord represents hashmap of required coordinates
   * @return
   */
  private boolean breaksCondition(List<List<Double>> coordinates, Map<String, Double> requiredCoord){
    for (List<Double> cord : coordinates){
      if (requiredCoord.containsKey("minLat")){
        if(cord.get(0) < requiredCoord.get("minLat")){
          return true;
        }
      }
      if (requiredCoord.containsKey("minLong")){
        if(cord.get(1) < requiredCoord.get("minLong")){
          return true;
        }
      }
      if (requiredCoord.containsKey("maxLat")){
        if(cord.get(0) > requiredCoord.get("maxLat")){
          return true;
        }
      }
      if (requiredCoord.containsKey("maxLong")){
        if (cord.get(1) > requiredCoord.get("maxLong")) {
          return true;
        }
      }
    }
    return false;
  }

}


# cs32-geojson-sample
This is a sample geojson parser that is able to parse the large geojson we need to use for Maps.
To run, go to Main > Run. In order to see the parsed object, put a breakpoint on line 13 and view geomapCollection.
[Note: when using this parser there are (I think 3) geomaps that don't get parsed because they lack a geometry field]

Breakdown of files:
- **JSONParser** takes in a filepath and parses the file into a GeoMapCollection object. You can access the data through getData() [Note: does not return a copy! future versions should include defensive programming]
- **GeoMapAdapter** uses Moshi to do fromJson() and toJson()
- GeoMapCollection folder:
    - **GeoMapCollection** object
    - GeoMap folder:
        - **GeoMap** object
        - **Geometry** object 
        - **Property** object
 

package com.tonyandollie.yafood;

import java.util.ArrayList;

import org.json.crockford.JSONArray;
import org.json.crockford.JSONException;
import org.json.crockford.JSONObject;

public class DayPart extends BaseModel {
	
	private String partName;
	private ArrayList<Station> stations;

	
    public static DayPart fromJson(JSONObject jsonObject) {
        DayPart dayPart = new DayPart();
        dayPart.jsonObject = jsonObject;

    	dayPart.partName = jsonObject.getString("txtDayPartDescription");
    	
//    	JSONArray names = new JSONArray("[\"tblStation\"]");
//    	Log.d("DEBUG", "DayPart names " + names.toString());
//    	JSONArray stationsArray = jsonObject.toJSONArray(names);
//    	Log.d("DEBUG", "DayPart stationsArray " + stationsArray.toString());
//    	dayPart.stations = Station.fromJson(stationsArray);
//    	Log.d("DEBUG", "DayPart stations " + dayPart.stations.toString());

    	try {
	    	JSONArray stationsArray = jsonObject.getJSONArray("tblStation");
	    	dayPart.stations = Station.fromJson(stationsArray);
    	} catch (JSONException e) {
    		try {
    			JSONObject stationObj = jsonObject.getJSONObject("tblStation");
    			dayPart.stations = new ArrayList<Station>(1);
    			dayPart.stations.add(Station.fromJson(stationObj));
    		} catch (JSONException ex) {
    			ex.printStackTrace();
    		}
    	}
        return dayPart;
    }
    
    
    public static ArrayList<DayPart> fromJson(JSONArray jsonArray) {

    	ArrayList<DayPart> dayParts = new ArrayList<DayPart>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {

        	JSONObject DayPartJson = null;
            
        	try {
            	DayPartJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            DayPart dayPart = DayPart.fromJson(DayPartJson);

            if (dayParts != null) {
                dayParts.add(dayPart);
            }
        
        }

        return dayParts;
    }    

    
    public String getName() {
    	return this.partName;
    }
    
    
    public ArrayList<Station> getStations() {
    	return this.stations;
    }
    
}

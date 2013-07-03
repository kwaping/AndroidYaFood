package com.tonyandollie.yafood;

import java.util.ArrayList;

import org.json.crockford.JSONArray;
import org.json.crockford.JSONObject;

public class DayPart extends BaseModel {
	
	private String partName;
	private ArrayList<Station> stations;

	
    public static DayPart fromJson(JSONObject jsonObject) {
        DayPart dayPart = new DayPart();
        dayPart.jsonObject = jsonObject;

    	dayPart.partName = jsonObject.getString("txtDayPartDescription");
    	
    	JSONArray stationsArray = new JSONArray();
    	
    	try {
	    	stationsArray = jsonObject.optJSONArray("tblStation");
	    	if (stationsArray.isNull(0)) {
	    		JSONObject station = jsonObject.optJSONObject("tblStation");
	    		stationsArray.put(station);
	    	}
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}

    	dayPart.stations = Station.fromJson(stationsArray);

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

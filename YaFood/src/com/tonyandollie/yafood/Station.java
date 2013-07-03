package com.tonyandollie.yafood;

import java.util.ArrayList;

import org.json.crockford.JSONArray;
import org.json.crockford.JSONObject;

import android.util.Log;

public class Station extends BaseModel {
	
	private String stationName;
	private ArrayList<Item> items;

	
    public static Station fromJson(JSONObject jsonObject) {
        Station station = new Station();
        station.jsonObject = jsonObject;
        Log.d("DEBUG", jsonObject.toString());

        station.stationName = jsonObject.getString("txtStationDescription");

        JSONArray itemsArray = new JSONArray();
        
    	try {
	        itemsArray = jsonObject.optJSONArray("tblItem");
	    	if (itemsArray.isNull(0)) {
	    		JSONObject item = jsonObject.optJSONObject("tblItem");
	    		itemsArray.put(item);
	    	}
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}
    	
    	station.items = Item.fromJson(itemsArray);

    	return station;
    }
    
    public static ArrayList<Station> fromJson(JSONArray jsonArray) {

    	ArrayList<Station> stations;
    	try {
    		stations = new ArrayList<Station>(jsonArray.length());
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    		stations = new ArrayList<Station>(0);
    	}

    	try {
			for (int i=0; i < jsonArray.length(); i++) {
	            JSONObject StationJson = null;
	            try {
	                StationJson = jsonArray.getJSONObject(i);
	            } catch (Exception e) {
	                e.printStackTrace();
	                continue;
	            }
	
	            Station station = Station.fromJson(StationJson);
	            if (stations != null) {
	                stations.add(station);
	            }
	        }
    	} catch (NullPointerException e) {
    		e.printStackTrace();
    	}

        return stations;
    }    

    public String getName() {
    	return this.stationName;
    }
    
    public ArrayList<Item> getItems() {
    	return this.items;
    }
    
}

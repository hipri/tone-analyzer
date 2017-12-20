package com.acn.acit.watsonservices.toneanalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.Tone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class GeneralToneAnalyzerDemo {
	public static void main(String[] args) {
		ToneAnalyzer service = new ToneAnalyzer("2017-09-21");
		service.setUsernameAndPassword("a4f7ab50-f544-4e37-a396-7393d3feca86", "53zhn3FrxlAG");

		try {
			JsonReader jReader = new JsonReader(new FileReader("tone.json"));
			JsonParser jParser = new JsonParser();
			JsonObject jObject = (JsonObject) jParser.parse(jReader);
			ToneOptions options = new ToneOptions.Builder().addTone(Tone.EMOTION).build();
			ToneAnalysis tone = service.getTone(jObject.get("text").getAsString(), options).execute();
			System.out.println(tone);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

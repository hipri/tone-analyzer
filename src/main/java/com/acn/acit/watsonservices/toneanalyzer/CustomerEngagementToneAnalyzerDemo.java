package com.acn.acit.watsonservices.toneanalyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneChatRequest;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.Utterance;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.UtterancesTone;

public class CustomerEngagementToneAnalyzerDemo {
	public static void main(String[] args) {
		ToneAnalyzer service = new ToneAnalyzer("2017-09-21");
		service.setUsernameAndPassword("a4f7ab50-f544-4e37-a396-7393d3feca86", "53zhn3FrxlAG");

		try {
			JsonReader jReader = new JsonReader(new FileReader("tone-chat.json"));
			JsonParser jParser = new JsonParser();
			JsonObject jObject = (JsonObject) jParser.parse(jReader);
			JsonArray jArray = jObject.getAsJsonArray("utterances");

			List<Utterance> utterances = new ArrayList();
			Iterator<JsonElement> iterator = jArray.iterator();
			while (iterator.hasNext()) {
				JsonObject jObj = (JsonObject) iterator.next();
				Utterance utterance = new Utterance.Builder().text(jObj.get("text").toString())
						.user(jObj.get("user").toString()).build();
				utterances.add(utterance);
			}

			ToneChatRequest options = new ToneChatRequest.Builder().utterances(utterances).build();
			UtterancesTone tone = service.getChatTone(options).execute();
			System.out.println(tone);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

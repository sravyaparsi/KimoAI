package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.mongodb.client.MongoDatabase;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.internal.MongoClientImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.bson.Document;
import org.json.JSONException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KimoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KimoApplication.class, args);

		//*** Import courses.json file to mongoDB
		String databaseName = "users";
		String connectionString = "mongodb://localhost:27017";
		String collectionName = "users_data";

		// JSON file path
		String jsonFilePath = "/com/example/demo/courses.json";

		// Create a MongoClient
		MongoClient mongoClient = MongoClients.create(connectionString);
		try {
			// Access the target database and collection
			MongoDatabase database = mongoClient.getDatabase(databaseName);
			MongoCollection<Document> collection = database.getCollection(collectionName);

			// Read the JSON file
			List<Document> documents = null;
			try (FileReader fileReader = new FileReader(jsonFilePath)) {
				Gson gson = new Gson();

				Type listType = new TypeToken<List<Document>>() {}.getType();
				documents = gson.fromJson(fileReader, listType);
				System.out.println("JSON imported successfully to MongoDB");
			} catch (Exception e) {
				e.printStackTrace();
			}

			collection.insertMany(documents);

		} finally {
			mongoClient.close();
		}
	}
}
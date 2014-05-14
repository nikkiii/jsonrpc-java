package org.nikkii.jsonrpc;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.nikkii.jsonrpc.exception.JsonRpcException;
import org.nikkii.jsonrpc.exception.JsonRpcParseException;
import org.nikkii.jsonrpc.gson.JsonRpcRequestDeserializer;
import org.nikkii.jsonrpc.gson.JsonRpcErrorSerializer;
import org.nikkii.jsonrpc.gson.JsonRpcResponseSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * The main JsonRpc class
 * 
 * @author Nikki
 *
 */
public class JsonRpc {
	public static final String VERSION = "2.0";
	
	/**
	 * The Gson Instance with registered deserializers/serializers
	 */
	public static final Gson GSON =
		new GsonBuilder()
			.registerTypeAdapter(JsonRpcRequest.class, new JsonRpcRequestDeserializer())
			.registerTypeAdapter(JsonRpcResponse.class, new JsonRpcResponseSerializer())
			.registerTypeAdapter(JsonRpcError.class, new JsonRpcErrorSerializer())
		.create();
	
	/**
	 * A single JsonParser instance
	 */
	private static final JsonParser PARSER = new JsonParser();
	
	/**
	 * Decode a request from a json string
	 * 
	 * @param json The JSON string
	 * @return The decoded request
	 * @throws org.nikkii.jsonrpc.exception.JsonRpcParseException If there was an exception while parsing the request
	 */
	public static JsonRpcRequest decodeRequest(String json) throws JsonRpcParseException {
		try {
			return GSON.fromJson(json, JsonRpcRequest.class);
		} catch (JsonParseException e) {
			throw new JsonRpcParseException(e);
		}
	}

	/**
	 * Decode a request from a json object
	 * 
	 * @param obj The JSON object
	 * @return The decoded request
	 * @throws org.nikkii.jsonrpc.exception.JsonRpcParseException If there was an exception while parsing the request
	 */
	public static JsonRpcRequest decodeRequest(JsonObject obj) throws JsonRpcParseException {
		try {
			return GSON.fromJson(obj, JsonRpcRequest.class);
		} catch (JsonParseException e) {
			throw new JsonRpcParseException(e);
		}
	}
	
	/**
	 * A map of JsonRpc Methods
	 */
	private Map<String, JsonRpcMethod> methods = new HashMap<>();
	
	public JsonRpc() {
		
	}
	
	/**
	 * Register a new method
	 * 
	 * @param method The method name
	 * @param callback The method callback
	 */
	public void registerMethod(String method, JsonRpcMethod callback) {
		if (method.startsWith("rpc.")) {
			throw new IllegalArgumentException("The rpc namespace is reserved for system extensions.");
		}
		
		methods.put(method, callback);
	}
	
	/**
	 * Register a new method
	 * 
	 * @param method The method name
	 * @param callback The method callback
	 */
	public void registerMethod(String method, Function<JsonRpcRequest, JsonElement> callback) {
		if (method.startsWith("rpc.")) {
			throw new IllegalArgumentException("The rpc namespace is reserved for system extensions.");
		}
		
		methods.put(method, callback);
	}
	
	/**
	 * Execute a JsonRpc request with the data from the reader and return the response
	 * 
	 * @param reader The reader to read the JSON value from
	 * @return The JsonElement containing the response
	 */
	public JsonElement execute(Reader reader) {
		return execute(PARSER.parse(reader));
	}
	
	/**
	 * Execute a JsonRpc request with the data from the string and return the response
	 * 
	 * @param json The string to parse the JSON from
	 * @return The JsonElement containing the response
	 */
	public JsonElement execute(String json) {
		return execute(PARSER.parse(json));
	}
	
	/**
	 * Execute a JsonRpc request with the data from the JsonElement object
	 * 
	 * @param obj The JsonElement to use
	 * @return The JsonElement containing the response, or null if it is a notification
	 */
	public JsonElement execute(JsonElement obj) {
		if (obj.isJsonArray()) {
			// Batch request
			JsonArray array = obj.getAsJsonArray();
			
			if (array.size() < 1) {
				// Empty batch means that it's a simple response, not an array
				return GSON.toJsonTree(new JsonRpcResponse(null, JsonRpcError.INVALID_REQUEST));
			}
			
			JsonArray responses = new JsonArray();
			
			for(int i = 0; i < array.size(); i++) {
				JsonElement element = array.get(i);
				
				if (!element.isJsonObject()) {
					// Non-empty array means we need to return a response in an array
					responses.add(GSON.toJsonTree(new JsonRpcResponse(null, JsonRpcError.INVALID_REQUEST)));
				} else {
					try {
						JsonRpcRequest request = decodeRequest(element.getAsJsonObject());
						
						JsonRpcResponse response = executeRequest(request);
						
						if (request.getId() != null) {
							responses.add(GSON.toJsonTree(response));
						}
					} catch (JsonRpcException e) {
						responses.add(GSON.toJsonTree(new JsonRpcResponse(null, e.getError())));
					}
				}
			}
			
			return responses.size() > 0 ? responses : null;
		} else {
			JsonRpcResponse response;

			try {
				JsonRpcRequest request = decodeRequest(obj.getAsJsonObject());
				
				response = executeRequest(request);
			} catch (JsonRpcException e) {
				response = new JsonRpcResponse(null, e.getError());
			}
			
			return response.getId() != null ? GSON.toJsonTree(response) : null;
		}
	}

	/**
	 * Execute a JsonRpc Request using the method map
	 * 
	 * @param request The request to execute
	 * @return The JsonRpcResponse (error or valid response)
	 */
	private JsonRpcResponse executeRequest(JsonRpcRequest request) {
		if (!methods.containsKey(request.getMethod())) {
			return new JsonRpcResponse(request.getId(), JsonRpcError.METHOD_NOT_FOUND);
		}
		JsonRpcMethod method = methods.get(request.getMethod());
		
		JsonRpcResponse response;
		
		try {
			JsonElement result = method.execute(request);
			response = new JsonRpcResponse(request.getId(), result);
		} catch (JsonRpcException e) {
			response = new JsonRpcResponse(request.getId(), e.getError());
		}
		
		return response;
	}
}

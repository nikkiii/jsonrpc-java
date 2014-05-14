package org.nikkii.jsonrpc.gson;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.nikkii.jsonrpc.JsonRpc;
import org.nikkii.jsonrpc.JsonRpcArgument;
import org.nikkii.jsonrpc.JsonRpcArgumentList;
import org.nikkii.jsonrpc.JsonRpcRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * A JsonDeserializer for decoding JsonRpcRequests and the params
 * 
 * @author Nikki
 *
 */
public class JsonRpcRequestDeserializer implements JsonDeserializer<JsonRpcRequest> {

	@Override
	public JsonRpcRequest deserialize(JsonElement element, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		JsonObject obj = element.getAsJsonObject();
		
		if (!obj.has("jsonrpc") || !obj.get("jsonrpc").getAsString().equals(JsonRpc.VERSION)) {
			throw new JsonParseException("Invalid JSONRPC request, expected 2.0, got " + obj.get("jsonrpc").getAsString());
		}
		
		List<JsonRpcArgument> arguments = new LinkedList<>();
		
		if (obj.has("params")) {
			JsonElement params = obj.get("params");
			
			if (params.isJsonArray()) {
				JsonArray paramArray = params.getAsJsonArray();
				
				for(int i = 0; i < paramArray.size(); i++) {
					arguments.add(new JsonRpcArgument(paramArray.get(i)));
				}
			} else if (params.isJsonObject()) {
				JsonObject paramObj = params.getAsJsonObject();
				
				for (Entry<String, JsonElement> entry : paramObj.entrySet()) {
					arguments.add(new JsonRpcArgument(entry.getKey(), entry.getValue()));
				}
			}
		}
		
		JsonRpcArgumentList list = new JsonRpcArgumentList(arguments.toArray(new JsonRpcArgument[0]));
		
		return new JsonRpcRequest(obj.get("id").getAsString(), obj.get("method").getAsString(), list);
	}

}

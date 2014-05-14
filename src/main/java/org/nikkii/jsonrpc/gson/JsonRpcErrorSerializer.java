package org.nikkii.jsonrpc.gson;

import java.lang.reflect.Type;

import org.nikkii.jsonrpc.JsonRpcError;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * A serializer to transform JsonRpcError into a JsonElement (with data being optional)
 * 
 * @author Nikki
 *
 */
public class JsonRpcErrorSerializer implements JsonSerializer<JsonRpcError> {

	@Override
	public JsonElement serialize(JsonRpcError error, Type type, JsonSerializationContext ctx) {
		JsonObject object = new JsonObject();
		
		object.addProperty("code", error.getCode());
		object.addProperty("message", error.getMessage());
		
		if (error.getData() != null) {
			object.addProperty("data", error.getData());
		}
		
		return object;
	}

}

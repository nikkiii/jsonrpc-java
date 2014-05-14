package org.nikkii.jsonrpc.gson;

import java.lang.reflect.Type;

import org.nikkii.jsonrpc.JsonRpc;
import org.nikkii.jsonrpc.JsonRpcResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * A serializer to serialize the JsonRpcResponse object
 * 
 * It will serialize either an error or successful response.
 * 
 * @author Nikki
 *
 */
public class JsonRpcResponseSerializer implements JsonSerializer<JsonRpcResponse> {

	@Override
	public JsonElement serialize(JsonRpcResponse response, Type type, JsonSerializationContext ctx) {
		JsonObject object = new JsonObject();
		
		object.addProperty("jsonrpc", JsonRpc.VERSION);
		object.addProperty("id", response.getId());
		
		if (response.getError() != null) {
			object.add("error", ctx.serialize(response.getError()));
		} else {
			object.add("result", response.getResult());
		}
		
		return object;
	}

}

package org.nikkii.jsonrpc;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * A JSONRPC Argument (Named or Indexed)
 * 
 * @author Nikki
 *
 */
public class JsonRpcArgument {
	/**
	 * The argument name, if it has one.
	 */
	private String name;
	
	/**
	 * The argument element
	 */
	private JsonElement value;
	
	/**
	 * Construct a new indexed element
	 * 
	 * @param value The element for this argument
	 */
	public JsonRpcArgument(JsonElement value) {
		this.value = value;
	}
	
	/**
	 * Construct a new named argument
	 * 
	 * @param name The argument name
	 * @param value The argument element
	 */
	public JsonRpcArgument(String name, JsonElement value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Get the value of this Argument
	 * 
	 * @return The JsonElement representing the value
	 */
	public JsonElement getValue() {
		return value;
	}
	
	/**
	 * Get this argument as a JsonPrimitive
	 * 
	 * @return The value, casted to JsonPrimitive
	 */
	public JsonPrimitive getAsJsonPrimitive() {
		return value.getAsJsonPrimitive();
	}
	
	/**
	 * Get this argument as an int
	 * 
	 * @return The value, casted to int
	 */
	public int getAsInt() {
		return value.getAsInt();
	}
	
	/**
	 * Get this argument as a String
	 * 
	 * @return The value, casted to String
	 */
	public String getAsString() {
		return value.getAsString();
	}
	
	/**
	 * Get this argument as a JsonObject
	 * 
	 * @return The value, casted to JsonObject
	 */
	public JsonObject getAsJsonObject() {
		return value.getAsJsonObject();
	}
	
	/**
	 * Get this argument as a JsonArray
	 * 
	 * @return The value, casted to JsonArray
	 */
	public JsonArray getAsJsonArray() {
		return value.getAsJsonArray();
	}

	/**
	 * Get the argument name
	 * 
	 * @return The argument name, or null if it is not named
	 */
	public String getName() {
		return name;
	}

	/**
	 * Check whether the argument has a name
	 * 
	 * @return True, if name is not null
	 */
	public boolean hasName() {
		return name != null;
	}
}

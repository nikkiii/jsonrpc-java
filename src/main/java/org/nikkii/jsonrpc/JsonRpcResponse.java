package org.nikkii.jsonrpc;

import com.google.gson.JsonElement;

/**
 * Represents a JsonRpc Response
 * 
 * @author Nikki
 *
 */
public class JsonRpcResponse {
	/**
	 * The id, usually from the request
	 */
	private String id;
	
	/**
	 * The error object, if this is set then result will be ignored.
	 */
	private JsonRpcError error;
	
	/**
	 * The result of the response
	 */
	private JsonElement result;

	/**
	 * Construct a new (error) response
	 * 
	 * @param id The request id
	 * @param error The error
	 */
	public JsonRpcResponse(String id, JsonRpcError error) {
		this.id = id;
		this.error = error;
	}

	/**
	 * Construct a new successful response
	 * 
	 * @param id The request id
	 * @param result The result as a JsonElement
	 */
	public JsonRpcResponse(String id, JsonElement result) {
		this.id = id;
		this.result = result;
	}

	/**
	 * Get the request/response id
	 * 
	 * @return The request/response id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get the error
	 * 
	 * @return The error object, or null if none
	 */
	public JsonRpcError getError() {
		return error;
	}
	
	/**
	 * Get the result
	 * 
	 * @return The result object, or null if none
	 */
	public JsonElement getResult() {
		return result;
	}
}

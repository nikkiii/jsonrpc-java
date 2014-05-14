package org.nikkii.jsonrpc;

import org.nikkii.jsonrpc.exception.JsonRpcException;

import com.google.gson.JsonElement;

/**
 * Represents a JsonRpc method when used with the normal system
 * 
 * @author Nikki
 *
 */
public interface JsonRpcMethod {
	/**
	 * Execute a JsonRpc method and return the result as a JsonElement
	 * 
	 * @param request The decoded JsonRpcRequest object
	 * @return A JsonElement containing the result
	 * @throws org.nikkii.jsonrpc.exception.JsonRpcException If an error occurs (Internal error or application error)
	 */
	public JsonElement execute(JsonRpcRequest request) throws JsonRpcException;
}

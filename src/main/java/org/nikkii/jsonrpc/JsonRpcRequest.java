package org.nikkii.jsonrpc;

/**
 * Represents a JsonRpcRequest
 * 
 * @author Nikki
 *
 */
public class JsonRpcRequest {
	/**
	 * The request id (Can be null for notifications)
	 */
	private final String id;
	
	/**
	 * The request method
	 */
	private final String method;
	
	/**
	 * The request arguments
	 */
	private final JsonRpcArgumentList argumentList;
	
	/**
	 * Construct a new JsonRpc Request
	 * 
	 * @param id The request id or null if it's a notification
	 * @param method The method name
	 * @param argumentList The argument list
	 */
	public JsonRpcRequest(String id, String method, JsonRpcArgumentList argumentList) {
		this.id = id;
		this.method = method;
		this.argumentList = argumentList;
	}

	/**
	 * Get the request id
	 * 
	 * @return The request id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the request method
	 * 
	 * @return The request method
	 */
	public String getMethod() {
		return method;
	}
	
	/**
	 * Shorthand for getArgumentList().getArgument()
	 * 
	 * @param idx The argument index
	 * @param name The argument name
	 * @return The argument, or null if not found
	 */
	public JsonRpcArgument getArgument(int idx, String name) {
		return argumentList.getArgument(idx, name);
	}

	/**
	 * Get the argument list
	 * 
	 * @return The argument list
	 */
	public JsonRpcArgumentList getArgumentList() {
		return argumentList;
	}
}

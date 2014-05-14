package org.nikkii.jsonrpc.exception;

import org.nikkii.jsonrpc.JsonRpcError;

/**
 * An exception which is used with JsonRpcError to signify an error
 * 
 * @author Nikki
 *
 */
public class JsonRpcException extends Exception {

	/**
	 * Generated Serial
	 */
	private static final long serialVersionUID = 3787285100090613844L;
	
	/**
	 * The error for this exception
	 */
	private JsonRpcError error;

	/**
	 * Construct a new exception with the specified error
	 * 
	 * @param error The JsonRpcError to use for the response
	 */
	public JsonRpcException(JsonRpcError error) {
		this.error = error;
	}
	
	/**
	 * Construct a new exception with the specified error and cause
	 * @param error The JsonRpcError to use for the response
	 * @param cause The cause to include in the response data
	 */
	public JsonRpcException(JsonRpcError error, Throwable cause) {
		super(cause);
		this.error = error;
	}
	
	/**
	 * Get the error
	 * 
	 * @return The error for this exception
	 */
	public JsonRpcError getError() {
		return error;
	}
}

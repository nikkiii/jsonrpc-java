package org.nikkii.jsonrpc;

/**
 * A JsonRpc Error (As defined in {@link http://www.jsonrpc.org/specification#error_object})
 * 
 * @author Nikki
 *
 */
public class JsonRpcError {
	
	/**
	 * Parse Error constant
	 */
	public static final JsonRpcError PARSE_ERROR = new JsonRpcError(-32700, "Parse Error");
	
	/**
	 * Invalid Request Error constant
	 */
	public static final JsonRpcError INVALID_REQUEST = new JsonRpcError(-32600, "Invalid Request");
	
	/**
	 * Method Not Found Error constant
	 */
	public static final JsonRpcError METHOD_NOT_FOUND = new JsonRpcError(-32601, "Method not found");
	
	/**
	 * Invalid Params error constant
	 */
	public static final JsonRpcError INVALID_PARAMS = new JsonRpcError(-32602, "Invalid Params");
	
	/**
	 * Internal Error constant
	 */
	public static final JsonRpcError INTERNAL_ERROR = new JsonRpcError(-32603, "Internal Error");
	
	/**
	 * The jsonrpc error code
	 */
	private final int code;
	
	/**
	 * The jsonrpc error message
	 */
	private final String message;
	
	/**
	 * The (optional) jsonrpc error data
	 */
	private final String data;
	
	/**
	 * Construct a new error with a code and message
	 * 
	 * @param code The jsonrpc error code
	 * @param message The jsonrpc error message
	 */
	public JsonRpcError(int code, String message) {
		this.code = code;
		this.message = message;
		this.data = null;
	}
	
	/**
	 * Construct a new error with a code, message, and data
	 * 
	 * @param code The jsonrpc error code
	 * @param message The jsonrpc error message
	 * @param data The jsonrpc error data
	 */
	public JsonRpcError(int code, String message, String data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * Get the error code
	 * 
	 * @return The error code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Get the error message
	 * @return The error message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get the error data
	 * 
	 * @return The error data
	 */
	public String getData() {
		return data;
	}
}

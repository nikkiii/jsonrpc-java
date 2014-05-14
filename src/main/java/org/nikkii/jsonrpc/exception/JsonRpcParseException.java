package org.nikkii.jsonrpc.exception;

import org.nikkii.jsonrpc.JsonRpcError;

/**
 * An exception for a Json parse error
 * 
 * @author Nikki
 *
 */
public class JsonRpcParseException extends JsonRpcException {

	/**
	 * Generated Serial
	 */
	private static final long serialVersionUID = -7477290106764524278L;

	/**
	 * Construct a new parse exception
	 */
	public JsonRpcParseException(Throwable cause) {
		super(JsonRpcError.PARSE_ERROR, cause);
	}

}

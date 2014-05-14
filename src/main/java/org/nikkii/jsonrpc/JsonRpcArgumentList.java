package org.nikkii.jsonrpc;

/**
 * A class holding JsonRpc arguments with a method to get by index or name
 * 
 * @author Nikki
 *
 */
public class JsonRpcArgumentList {
	
	/**
	 * Array of JsonRpc Arguments
	 */
	private final JsonRpcArgument[] arguments;
	
	/**
	 * Construct a new argument list
	 * 
	 * @param arguments The JsonRpc arguments
	 */
	public JsonRpcArgumentList(JsonRpcArgument[] arguments) {
		this.arguments = arguments;
	}
	
	/**
	 * Get the argument at the specified index or by name
	 * 
	 * @param idx The argument index
	 * @param name The argument name
	 * @return The argument, or null if not found
	 */
	public JsonRpcArgument getArgument(int idx, String name) {
		if (idx < 0 || idx > arguments.length) {
			throw new IllegalArgumentException("Invalid argument index");
		}
		// Check index first, since it's faster than looping usually
		if (!arguments[idx].hasName() || name.equals(arguments[idx].getName())) {
			return arguments[idx];
		}
		
		// Check each argument for the specified name
		for (int i = 0; i < arguments.length; i++) {
			if (arguments[i].hasName() && arguments[i].getName().equals(name)) {
				return arguments[i];
			}
		}
		
		return null;
	}
}

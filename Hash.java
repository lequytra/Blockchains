package blockChain;

import java.util.Arrays;

public class Hash {
	private byte[] data; 
	
	public Hash (byte[] data) {
		this.data = data; 
	}
	
	public byte[] getData() {
		return data; 
	}
	
	/**
	 * Check whether the hash is valid - start with 3 zeros
	 * @return boolean - true if the hash is valid
	 */
	public boolean isValid() {
		for (int i = 0; i < 3; i++) {
			if(data[i] != 0) return false; 
		}
		return true; 
	}
	
	/**
	 * Check whether two object has the same hash
	 * @return return true if they have the same hash
	 */
	public boolean equals(Object other) {
		if (other instanceof Hash) {
			Hash hash = (Hash) other; 
			if (Arrays.equals(data, hash.getData())) return true; 
		}
		return false; 
	}
	
	/**
	 * return the string representation of the hash in hexadecimal format. 
	 */
	public String toString() {
		int len = data.length; 
		int [] integer = new int [len]; 
		String formatted = ""; 
		for (int i = 0; i < len; i++) {
			integer[i] = Byte.toUnsignedInt(data[i]); 
			formatted += String.format("%02X", integer[i]); 
		}
		return formatted; 
	}
}

package blockChain;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import blockChain.Hash; 

public class Block {
	private int blockNum; 
	private int amount;
	private Hash prevHash;
	private long nonce; 
	private Hash hash; 
	
	/**
	 * Initialize a new Block given the parameters. 
	 * Perform mining to find the appropriate nonce. 
	 * @param blockNum
	 * @param amount
	 * @param prevHash
	 * @throws NoSuchAlgorithmException
	 */
	public Block (int blockNum, int amount, Hash prevHash) throws NoSuchAlgorithmException {
		this.blockNum = blockNum; 
		this.amount = amount; 
		this.prevHash = prevHash; 
		//perform mining to find the appropriate nonce. 
		do {
			this.nonce++; 
			this.hash = calculateHash(blockNum, amount, prevHash, nonce); 
		} while (!this.hash.isValid()); 
	}
	/**
	 * Initialize a new Block given the parameters. 
	 * @param blockNum
	 * @param amount
	 * @param prevHash
	 * @param nonce
	 * @throws NoSuchAlgorithmException
	 */
	public Block (int blockNum, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
		this.blockNum = blockNum; 
		this.amount = amount; 
		this.prevHash = prevHash; 
		this.nonce = nonce; 
		this.hash = calculateHash(blockNum, amount, prevHash, nonce);
	}
	
	/**
	 * Calculate the Hash for one block 
	 * @param blockNum
	 * @param amount
	 * @param prevHash
	 * @param nonce
	 * @return Hash 
	 * @throws NoSuchAlgorithmException
	 */
	public static Hash calculateHash(int blockNum, int amount, Hash prevHash, long nonce) 
						throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("sha-256");
	    ByteBuffer numArray = ByteBuffer.allocate(8).putInt(blockNum).putInt(amount); 
		ByteBuffer nonceArray = ByteBuffer.allocate(8).putLong(nonce);
		md.update(numArray.array());
		if (prevHash != null) {
			md.update(prevHash.getData()); //if the block is not at the beginning of the chain 
										   //then update MessageDigest with its previous Hash
		}
		md.update(nonceArray.array());
	    Hash hash = new Hash(md.digest());
	    return hash;
	}
	
	
	public int getNum() {
		return blockNum; 
	}
	
	public int getAmount() {
		return amount; 
	}
	
	public long getNonce() {
		return nonce; 
	}
	
	public Hash getPrevHash() {
		return prevHash; 
	}
	
	public Hash getHash() {
		return hash; 
	}
	
	/**
	 * @return a string representation of all the information in the Block. 
	 */
	public String toString() {
		return "Block <" + blockNum + "> (Amount: <" + amount + ">, Nonce: <" + nonce
				+ ">, prevHash: <" + prevHash + ">, hash: <" + hash + ">";
	}
}

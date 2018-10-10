package blockChain;

import java.security.NoSuchAlgorithmException;

import blockChain.Block; 

public class BlockChain {
	private Node first;
	protected Node last; 
	private int numBlock = 0; 
	
	public static class Node {
		protected Block block; 
		private Node next; 
		
		public Node (Block block){
			this.block = block; 
		}
	}
	
	/**
	 * Initialize a new blockchain with a given initial amount of money 
	 * @param initial the initial amount of money in account
	 * @throws NoSuchAlgorithmException
	 */
	public BlockChain(int initial) throws NoSuchAlgorithmException {
		Block tempBlock = new Block(0, initial, null); 
		Node node1 = new Node(tempBlock); 
		first = node1; 
		last = node1; 
		this.numBlock++; 
	}
	
	/**
	 * mine a valid nonce for a new block given the monetary amount of the transaction. 
	 * @param amount
	 * @return Block a new block that stores all the new data
	 * @throws NoSuchAlgorithmException
	 */
	public Block mine(int amount) throws NoSuchAlgorithmException {
		Hash prevHash = last.block.getHash(); 
		Block newBlock = new Block(numBlock, amount, prevHash); 
		return newBlock; 
	}
	
	public int getSize() {
		return numBlock; 
	}
	
	/**
	 * append the a block to the blockchain
	 * @param blk
	 * @throws IllegalArgumentException
	 */
	public void append(Block blk) throws IllegalArgumentException {
			Node newNode = new Node(blk); 
			last.next = newNode; 
			last = newNode; 
			this.numBlock++; 
	}
	
	/**
	 * Remove the last block of the chain
	 * @return boolean - true if the removal was successful
	 * 				   - false if it fails to remove the block, 
	 * 						or the chain only contains one block
	 */
	public boolean removeLast() {
		if(numBlock > 1) {
			Node p = first; 
			while(p.next.next != null) {
				p = p.next; 
			}
			last = p; 
			p.next = null; 
			return true; 
		}
		return false; 
	}
	
	/**
	 * Check the integrity of the chain by comparing the prevHash 
	 * with the Hash of the previous block 
	 * @return	true if all Hash fields are consistent. 
	 * 			false otherwise
	 */
	public boolean isValidBlockChain() {
		Node p = first; 
		if (p != null) {
			int initial = p.block.getAmount();
			p = p.next; 
			int accountBob = 0; 
			while (accountBob <= initial && accountBob >= 0) {
				if(p.next == null) return true; 
				else {
					accountBob += p.block.getAmount(); 
					if (p.block.getHash().equals(p.next.block.getPrevHash())) return false; 
					p = p.next; 
				}
				return false; 
			}
		}
		return false; 
	}
	
	/**
	 * Calculate and print the account balances of two parties
	 */
	public void printBalances() {
		Node p = first; 
		int initial = p.block.getAmount(); 
		int accountAlice = initial; 
		for(;p != null; p = p.next) {
			accountAlice += p.block.getAmount();
		}
		System.out.println("Alice: " + accountAlice + "\tBob: " + (initial - accountAlice));  
	}
	
	/**
	 * @return a string representation of the blockchain, 
	 * 			including all the information contained in each block. 
	 */
	public String toString() {
		Node p = first; 
		StringBuilder history = new StringBuilder(); 
		for (;p != null; p = p.next) {
			history.append(p.block.toString());
			history.append("\n"); 
		}
		return history.toString(); 
	}
	
}

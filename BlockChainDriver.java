package blockChain;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import blockChain.Block;
import blockChain.Hash;
import blockChain.BlockChain;

public class BlockChainDriver {
	private static boolean active = true; 
	
	public static void printHelp() { //Message to be printed out to instruct users
		System.out.println("mine: discovers the nonce for a given transaction");
		System.out.println("append: appends a new block onto the end of the chain");
		System.out.println("remove: removes the last block from the end of the chain");
		System.out.println("check: checks that the block chain is valid");
		System.out.println("report: reports the balances of Alice and Bob");
		System.out.println("help: prints this list of commands");
		System.out.println("quit: quits the program");
	}
	
	public static void main (String [] args) throws NoSuchAlgorithmException {
		System.out.println("What is the initial amount you want to put in your account?");
		Scanner initial = new Scanner(System.in); 
		int initialAmount = Integer.parseInt(initial.nextLine().trim()); 
		BlockChain blockchain = new BlockChain(300); 
		
		while (active) {
			System.out.println(blockchain.toString());
			System.out.println("Command? ");
			String command = new Scanner(System.in).nextLine(); 
			switch(command) {
				case "help": 
					printHelp(); 
					break; 
				case "mine": 
					System.out.println("Amount transferred?  ");
					int amount = new Scanner(System.in).nextInt(); 
					Block block = blockchain.mine(amount); 
					System.out.println("amount = " + amount + "\tnonce = " + block.getNonce());
					break; 
				case "append":
					System.out.println("Amount transferred?  ");
					int transferred = new Scanner(System.in).nextInt();  
					System.out.println("Nonce?  ");
					int tempNonce = new Scanner(System.in).nextInt();
					blockchain.append(new Block(blockchain.getSize(), transferred, blockchain.last.block.getHash(), tempNonce));
					break; 
				case "remove":
					if (blockchain.removeLast()) System.out.println("Remove the last block successfully.");
					else System.out.println("Fail to remove the last block.");
					break;
				case "check":
					if (blockchain.isValidBlockChain()) System.out.println("Chain is valid!");
					else System.out.println("Chain is not valid.");
					break;
				case "report": 
					blockchain.printBalances();
					break; 
				case "quit": 
					active = false; 
					break; 
				default:
					System.out.println("Invalid command. Type help to see plausible actions.");
					break; 
					
			}
		}
	}
}

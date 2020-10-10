package com.cg.onlinewallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlinewallet.service.*;


@CrossOrigin(origins="*")
@RestController
public class OnlineWalletController {

	@Autowired
	private OnlineWalletService owService;

	public OnlineWalletController() {
		// TODO Auto-generated constructor stub
	}
	/*********************************************************************************************************************
	* Method:login
	* Description:To login the user into the application
	* 
	* @param email:User's email
	* 
	* @param password:User's password
	* 
	* @returns Entity:After login,it will return the userId
	* .
	* Created By-Kartik Shukla
	***********************************************************************************************************************/

	@GetMapping("/login")
	public ResponseEntity<Integer> login(String email, String password)
	{
		Integer userId = owService.login(email, password);
		return new ResponseEntity<Integer>(userId, HttpStatus.OK);
	}
	 
	
	/*********************************************************************************************************************
	* Method:transactMoney
	* 
	* Description:Transfer the money from one account to another account
	* 
	* @param userId:User's Id
	* 
	* @param amount:amount that is to be transferred
	* 
	* @returns Entity:After transaction,it will give the message that transaction is completed
	* 
	* Created By-Kartik Shukla
	***********************************************************************************************************************/

	@GetMapping("/transactmoney/{userId}")
	public ResponseEntity<String> transactMoney(@PathVariable("userId") Integer userId, Double amount,
			String email)
	{
		owService.transactMoney(userId, email, amount);
		return new ResponseEntity<String>("Transaction Completed", HttpStatus.OK);
	}
	
	/*********************************************************************************************************************
	* Method:showBalance
	* 
	* Description:To map the request of user for showing the balance available in the account
	* 
	* @param userId:User's Id
	* 
	* @returns Entity: the balance that is available in the account.
	* 
	* Created By-Kartik Shukla
	***********************************************************************************************************************/

	@GetMapping("/showbalance/{userId}")
	public ResponseEntity<Double> showBalance(@PathVariable("userId") Integer userId) {
		Double balance = owService.showBalance(userId);
		return new ResponseEntity<Double>(balance, HttpStatus.OK);
	}
	
	
}

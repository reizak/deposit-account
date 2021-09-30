package com.deposit.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deposit.model.Account;
import com.deposit.model.CalculateDepositAccountRequest;
import com.deposit.model.CreateDepositAccountRequest;
import com.deposit.model.Customer;
import com.deposit.model.Deposit;
import com.deposit.model.Parameter;
import com.deposit.model.Response;
import com.deposit.service.IAccountService;
import com.deposit.service.ICustomerService;
import com.deposit.service.IDepositService;
import com.deposit.service.IParameterService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Deposit Account", tags = "Deposit Account")
public class DepositAccountController {

	private static final Logger log = LoggerFactory.getLogger(DepositAccountController.class);
	
	private static final String DATE_FORMATE = "dd-MM-yyyy";
	private static final String STRING_USER = "USER";
	private static final String REGEX_NUMBER = "[^\\d]";
	public static final String SUCCESS_CODE = "00";
	public static final String NOT_SUCCESS_CODE = "01";
	public static final String DATA_NOT_FOUND_CODE = "04";

	public static final String SUCCESS_DESCR = "Success";
	public static final String NOT_SUCCESS_DESCR = "Not Success";
	public static final String DATA_NOT_FOUND_DESCR = "Data Not Found";


	@Autowired
	IAccountService accountService;

	@Autowired
	ICustomerService customerService;

	@Autowired
	IDepositService depositService;

	@Autowired
	IParameterService parameterService;

	@GetMapping("/deposit-account/{id}")
	public ResponseEntity<Response> getById(@PathVariable(value = "id") String accountNumber) {
		Response response = new Response();
		try {
			Account account = accountService.findByAccountNumber(accountNumber);
			if (account != null) {
				response.setCode("00");
				response.setMessage("Success");
				response.setResult(account);
			} else {
				response.setCode("01");
				response.setMessage("Account not found");
			}

		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return ResponseEntity.accepted().body(response);
	}

	@GetMapping("/deposit-accounts")
	public ResponseEntity<Response> getAll() {
		Response response = new Response();
		try {
			List<Account> account = accountService.findAll();
			if (account != null) {
				response.setCode("00");
				response.setMessage("Success");
				response.setResult(account);
			} else {
				response.setCode("01");
				response.setMessage("Account not found");
			}

		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		return ResponseEntity.accepted().body(response);
	}

	@PostMapping("/deposit-account/create")
	public ResponseEntity<Response> createDepositAccount(@RequestBody CreateDepositAccountRequest request) {
		Response response = new Response();
		Account account = new Account();
		Customer customer = new Customer();
		Deposit deposit = new Deposit();

		try {

			if (request.getLegalIdNumber().isEmpty()) {
				throw new Exception("legalIdNumber is Mandatory");
			}
			
			if (com.deposit.utils.StringFunction.isContainRegex(request.getLegalIdNumber(), REGEX_NUMBER)) {
				throw new Exception("legalIdNumber Format value is not valid");
			} 

			if (request.getLegalIdType().isEmpty()) {
				throw new Exception("legalIdType is Mandatory");
			}

			if (request.getAccountName().isEmpty()) {
				throw new Exception("accountName is Mandatory");
			}

			if (request.getDateOfBirth().isEmpty()) {
				throw new Exception("dateOfBirth is Mandatory");
			}
			
			boolean bod = true;
			
			SimpleDateFormat inputFormat = new SimpleDateFormat(DATE_FORMATE);
			try {
				inputFormat.parse(request.getDateOfBirth());
			} catch (Exception e) {
				bod = false;
			}
			
			if (bod) {
				throw new Exception("dateOfBirth Format value is not valid");
			}
			
			Parameter parameter = parameterService.findByCode("MIN_FIRST_DEPOSIT_AMOUNT");

			if (request.getFirstDepositAmount().isEmpty()) {
				throw new Exception("firstDepositAmount Format value is not valid");
			}
			
			if (com.deposit.utils.StringFunction.isContainRegex(request.getFirstDepositAmount(), REGEX_NUMBER)) {
				throw new Exception("firstDepositAmount Format value is not valid");
			} 

			if (new BigDecimal(request.getFirstDepositAmount()).compareTo(new BigDecimal(parameter.getValue())) == -1) {
				throw new Exception("firstDepositAmount must be greater then ".concat(parameter.getValue()));
			}

			if (request.getMonthlyDepositAmount().isEmpty()) {
				throw new Exception("monthlyDepositAmount Format value is not valid");
			}
			
			if (com.deposit.utils.StringFunction.isContainRegex(request.getMonthlyDepositAmount(), REGEX_NUMBER)) {
				throw new Exception("monthlyDepositAmount Format value is not valid");
			} 

			parameter = parameterService.findByCode("MIN_MONTHLY_DEPOSIT_AMOUNT");

			if (new BigDecimal(request.getMonthlyDepositAmount()).compareTo(new BigDecimal(parameter.getValue())) == -1) {
				throw new Exception("monthlyDepositAmount must be greater then ".concat(parameter.getValue()));
			}

			if (request.getPlaceOfBirth().isEmpty()) {
				throw new Exception("placeOfBirth Format value is not valid");
			}

			if (request.getPurposeOfSaving().isEmpty()) {
				throw new Exception("purposeOfSaving is Mandatory");
			}

			if (request.getSavingTenor().isEmpty()) {
				throw new Exception("savingTenor Format value is not valid");
			}
			
			if (com.deposit.utils.StringFunction.isContainRegex(request.getSavingTenor(), REGEX_NUMBER)) {
				throw new Exception("savingTenor Format value is not valid");
			}
			
			parameter = parameterService.findByCode("TENOR");
			
			if (!parameter.getValue().contains(String.valueOf(request.getSavingTenor()))) {
				throw new Exception("savingTenor not include : ".concat(parameter.getValue()));
			}
			
			if (request.getMobilePhone().isEmpty()) {
				throw new Exception("mobilePhone is Mandatory");
			}
			
			if (com.deposit.utils.StringFunction.isContainRegex(request.getMobilePhone(), REGEX_NUMBER)) {
				throw new Exception("mobilePhone Format value is not valid");
			}
			

			customer = customerService.findByLegalIdNumber(request.getLegalIdNumber());
			if (customer == null) {
				customer = new Customer();
				customer.setCustomerNumber(customerService.generateCustomerNumber());
				customer.setCustomerName(request.getAccountName());
				customer.setDateOfBirth(request.getDateOfBirth());
				customer.setPlaceOfBirth(request.getPlaceOfBirth());
				customer.setLegalIdType(request.getLegalIdType());
				customer.setLegalIdNumber(request.getLegalIdNumber());
				customer.setMobilePhone(request.getMobilePhone());
				customer.setCreatedBy(STRING_USER);
				customer.setCreatedDate(new Date());
				customer.setModifiedBy(STRING_USER);
				customer.setModifiedDate(new Date());
				customerService.save(customer);
			}
			request.setCustomerNumber(customer.getCustomerNumber());
			parameter = parameterService.findByCode("DEPOSIT_ACCOUNT_CATEGORY");
			account.setAccountCategory(parameter.getValue());
			account.setAccountNumber(accountService.generateAccountNumber());
			account.setCustomer(customer);			
			account.setCreatedBy(STRING_USER);
			account.setCreatedDate(new Date());
			account.setModifiedBy(STRING_USER);
			account.setModifiedDate(new Date());
			accountService.save(account);
			
			request.setAccountNumber(account.getAccountNumber());
			
			deposit.setAccountNumber(account.getAccountNumber());
			deposit.setFirstDepositAmount(request.getFirstDepositAmount());
			deposit.setMonthlyDepositAmount(request.getMonthlyDepositAmount());
			deposit.setSavingPurpose(request.getPurposeOfSaving());
			deposit.setTenor(request.getSavingTenor());
			deposit.setCreatedBy(STRING_USER);
			deposit.setCreatedDate(new Date());
			deposit.setModifiedBy(STRING_USER);
			deposit.setModifiedDate(new Date());
			depositService.save(deposit);
			
			response.setResult(request);
			response.setCode(SUCCESS_CODE);
			response.setMessage(SUCCESS_DESCR);
		} catch (Exception e) {
			response.setCode(NOT_SUCCESS_CODE);
			response.setMessage(NOT_SUCCESS_DESCR.concat(" - ").concat(e.getMessage()));
			log.error(e.getMessage());
			response.setMessage(e.getMessage());
		}

		return ResponseEntity.accepted().body(response);

	}

	@PostMapping("/deposit-account/calculate")
	public ResponseEntity<Response> calculateDepositAccount(@RequestBody CalculateDepositAccountRequest request) {
		Response response = new Response();

		try {
			if (request.getFirstDepositAmount() == null) {
				throw new Exception("firstDepositAmount is Mandatory");
			}

			if (request.getMonthlyDepositAmount() == null) {
				throw new Exception("monthlyDepositAmount is Mandatory");
			}

			if (request.getPurposeOfSaving().isEmpty()) {
				throw new Exception("purposeOfSaving is Mandatory");
			}

			if (request.getSavingTenor() == 0) {
				throw new Exception("savingTenor is Mandatory");
			}
			
			Parameter parameter = parameterService.findByCode("MIN_FIRST_DEPOSIT_AMOUNT");

			if (request.getFirstDepositAmount().compareTo(new BigDecimal(parameter.getValue())) == -1) {
				throw new Exception("firstDepositAmount must be greater then ".concat(parameter.getValue()));
			}
			
			parameter = parameterService.findByCode("MIN_MONTHLY_DEPOSIT_AMOUNT");

			if (request.getMonthlyDepositAmount().compareTo(new BigDecimal(parameter.getValue())) == -1) {
				throw new Exception("monthlyDepositAmount must be greater then ".concat(parameter.getValue()));
			}				
			
			parameter = parameterService.findByCode("TENOR");
			
			if (!parameter.getValue().contains(String.valueOf(request.getSavingTenor()))) {
				throw new Exception("savingTenor : ".concat(parameter.getValue()));
			}
			
			parameter = parameterService.findByCode("RATE_DEPOSIT");
			// balance = (((saving tenor/12) * 6%) * grand total deposit amount) + grand total deposit amount
			BigDecimal grandTotalAmount = request.getFirstDepositAmount()
					.add(request.getMonthlyDepositAmount().multiply(new BigDecimal(request.getSavingTenor())));
			double tenor = request.getSavingTenor();
			double rate = (double) Double.valueOf(parameter.getValue()) / 100;
			rate = ((tenor / 12) * (rate));
			BigDecimal balance = (new BigDecimal(rate).multiply(grandTotalAmount)).add(grandTotalAmount);
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			decimalFormat.setRoundingMode(RoundingMode.CEILING);
			response.setResult(decimalFormat.format(balance));
			response.setCode(SUCCESS_CODE);
			response.setMessage(SUCCESS_DESCR);
		} catch (Exception e) {
			response.setCode(NOT_SUCCESS_CODE);
			response.setMessage(NOT_SUCCESS_DESCR);
			log.error(e.getMessage());
			response.setMessage(e.getMessage());
		}

		return ResponseEntity.accepted().body(response);

	}

}

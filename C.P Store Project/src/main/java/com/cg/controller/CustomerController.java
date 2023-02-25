package com.cg.controller;

import com.cg.model.Customer;
import com.cg.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String showListPage(Model model) {
        List<Customer> customers = customerService.findAllByDeletedIsFalse();
        model.addAttribute("customers", customers);
        return "customer/index";
    }

//    @GetMapping
//    public String showListPage(Model model) {
//        List<Customer> customers = customerService.findAllByDeletedIsFalse();
//        model.addAttribute("customers", customers);
//        return "customer/list";
//    }

//    @GetMapping
//    public String showLoginForm(Model model) {
//
//        return "customer/login";
//    }


    @PostMapping("/update/{id}")
    public String doUpdate(@PathVariable Long id, @ModelAttribute Customer customer, Model model) {

        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            model.addAttribute("error", true);
        }
        else {
            customer.setId(id);
            customerService.save(customer);
            model.addAttribute("customer", customer);
        }

        return "customer/update";
    }

//    @PostMapping("/deposit/{customerId}")
//    public String doDeposit(@PathVariable Long customerId, @Validated @ModelAttribute Deposit deposit, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasFieldErrors()) {
//            model.addAttribute("error", true);
//            model.addAttribute("deposit", deposit);
//            return "customer/deposit";
//        }
//
//        Optional<Customer> customerOptional = customerService.findById(customerId);
//
//        if (!customerOptional.isPresent()) {
//            model.addAttribute("error", true);
//        }
//        else {
//            Customer customer = customerOptional.get();
//
//            deposit.setCustomer(customer);
//            deposit = customerService.deposit(deposit);
//
//            deposit.setTransactionAmount(BigDecimal.ZERO);
//
//            model.addAttribute("deposit", deposit);
//        }
//
//        model.addAttribute("success", true);
//        model.addAttribute("messages", "Deposit successful");
//
//        return "customer/deposit";
//    }

//    @PostMapping("/transfer/{senderId}")
//    public String doTransfer(@PathVariable Long senderId, @ModelAttribute Transfer transfer, Model model) {
//
//        Optional<Customer> senderOptional = customerService.findById(senderId);
//
//        List<Customer> recipients = customerService.findAllByIdNotAndDeletedIsFalse(senderId);
//
//        model.addAttribute("recipients", recipients);
//
//        if (!senderOptional.isPresent()) {
//            model.addAttribute("transfer", transfer);
//
//            model.addAttribute("error", true);
//            model.addAttribute("messages", "Sender not valid");
//
//            return "customer/transfer";
//        }
//
//        Long recipientId = transfer.getRecipient().getId();
//
//        Optional<Customer> recipientOptional = customerService.findById(recipientId);
//
//        if (!recipientOptional.isPresent()) {
//            model.addAttribute("transfer", transfer);
//
//            model.addAttribute("error", true);
//            model.addAttribute("messages", "Recipient not valid");
//
//            return "customer/transfer";
//        }
//
//        if (senderId.equals(recipientId)) {
//            model.addAttribute("error", true);
//            model.addAttribute("messages", "Sender ID must be different from Recipient ID");
//
//            return "customer/transfer";
//        }
//
//        BigDecimal senderCurrentBalance = senderOptional.get().getBalance();
//
//        BigDecimal transferAmount = transfer.getTransferAmount();
//        long fees = 10L;
//        BigDecimal feesAmount = transferAmount.multiply(BigDecimal.valueOf(fees)).divide(BigDecimal.valueOf(100));
//        BigDecimal transactionAmount = transferAmount.add(feesAmount);
//
//        if (senderCurrentBalance.compareTo(transactionAmount) < 0) {
//            model.addAttribute("error", true);
//            model.addAttribute("messages", "Sender balance not enough to transfer");
//
//            return "customer/transfer";
//        }
//
//        transfer.setSender(senderOptional.get());
//        transfer.setFees(fees);
//        transfer.setFeesAmount(feesAmount);
//        transfer.setTransactionAmount(transactionAmount);
//
//        customerService.transfer(transfer);
//
//        transfer.setTransferAmount(BigDecimal.ZERO);
//        transfer.setTransactionAmount(BigDecimal.ZERO);
//
//        model.addAttribute("transfer", transfer);
//
//        model.addAttribute("success", true);
//        model.addAttribute("messages", "Transfer success");
//
//        return "customer/transfer";
//    }
}

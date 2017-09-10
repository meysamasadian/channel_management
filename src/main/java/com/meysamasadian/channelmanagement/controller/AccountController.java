package com.meysamasadian.channelmanagement.controller;

import com.meysamasadian.channelmanagement.dto.AccountDto;
import com.meysamasadian.channelmanagement.dto.TransactionDto;
import com.meysamasadian.channelmanagement.exception.BusinessException;
import com.meysamasadian.channelmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by rahnema on 9/6/2017.
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST )
    @ResponseBody
    public String register(@RequestBody AccountDto accountDto) {
        try {
            accountService.register(accountDto);
            return accountDto.getFullName() + " was registered successfully!";
        } catch (BusinessException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @RequestMapping(value = "/login/{pan}", method = RequestMethod.POST )
    @ResponseBody
    public String login(@PathVariable String pan) {
        try {
            return accountService.login(pan);
        } catch (BusinessException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/transfer/{otp}", method = RequestMethod.POST )
    @ResponseBody
    public String transfer(@PathVariable String otp, @RequestBody TransactionDto dto) {
        try {
            accountService.transfer(dto, otp);
            return "money was transfer successfully!";
        } catch (BusinessException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/list/{phone}", method = RequestMethod.GET )
    @ResponseBody
    public List<TransactionDto> transfer(@PathVariable String phone) {
        return accountService.list(phone);
    }
}

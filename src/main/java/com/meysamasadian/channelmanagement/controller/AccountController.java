package com.meysamasadian.channelmanagement.controller;

import com.meysamasadian.channelmanagement.dto.AccountDto;
import com.meysamasadian.channelmanagement.dto.TransactionDto;
import com.meysamasadian.channelmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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
        accountService.register(accountDto, new BigDecimal(2000));
        return accountDto.getFullName() + " was registered successfully!";
    }

    @RequestMapping(value = "/login/{pan}", method = RequestMethod.POST )
    @ResponseBody
    public String login(@PathVariable String pan) {
        return accountService.login(pan);
    }

    @RequestMapping(value = "/transfer/{pan}", method = RequestMethod.POST )
    @ResponseBody
    public String transfer(@PathVariable String pan, @RequestBody TransactionDto dto) {
        accountService.transfer(dto, pan);
        return "money was transfer successfully!";
    }

    @RequestMapping(value = "/reverse/{pan}", method = RequestMethod.POST )
    @ResponseBody
    public String transfer(@PathVariable String pan, @RequestBody AccountDto dto) {
        accountService.reverse(dto, pan);
        return "money was reverse successfully!";
    }
}

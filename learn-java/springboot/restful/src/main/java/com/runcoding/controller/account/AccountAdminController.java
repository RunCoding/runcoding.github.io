package com.runcoding.controller.account;

import com.runcoding.pojo.po.account.Account;
import com.runcoding.service.AccountService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account/admin/api")
@Api(value = "account_admin", description = "账号管理")
public class AccountAdminController {

    private static Logger logger = LoggerFactory.getLogger(AccountAdminController.class);

    @Autowired
    private AccountService accountService;




    @GetMapping(value = "/{id}")
    @ApiOperation(value="获取用户", notes="通过账号id获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "账号id", paramType="path" , required = true, dataType = "Long"),
            @ApiImplicitParam(name = "username", value = "账号名称", paramType="query" , required = false, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求已完成",response=Account.class),
    })
    public Account findByAccount(@PathVariable Long id,
                                 @RequestParam(value = "username",defaultValue = "")String username) {

        return accountService.findAccountById(id);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value="保存账号", notes="新增账号")
    @ApiImplicitParam(name = "account", value = "用户详细实体account", required = true, dataType = "Account")
    public boolean addAccount(Account account) {
        return accountService.saveByAccount(account);
    }


}

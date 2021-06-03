package com.example.zkspringbootweb.vm;

import com.example.zkspringbootweb.entity.AppUser;
import com.example.zkspringbootweb.service.UserService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@NoArgsConstructor
@Data
@VariableResolver(DelegatingVariableResolver.class)
public class MainVM {

    String currentPage = "~./zul/app/page/page_home.zul";

    @Autowired
    private UserService userService;

    @Command
    @NotifyChange({"currentPage"})
    public void navigate(@BindingParam("page") String page) {
        this.currentPage = page;
    }

    @Command
    public void logout() {
        SecurityContextHolder.clearContext();
        Executions.sendRedirect("/logout");
    }

    public AppUser whoami() {
        return userService.whoami();
    }


}
package com.example.zkspringbootweb.vm;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@Data
@NoArgsConstructor
@VariableResolver(DelegatingVariableResolver.class)
public class AppVM {

    String currentPage = "~./zul/app/page/page_dashboard.zul";

    @Command
    @NotifyChange({"currentPage"})
    public void navigate(@BindingParam("page") String page) {
        this.currentPage = "~./zul/app/page/" + page;
    }

    @Command
    public void logout() {
        SecurityContextHolder.clearContext();
        Executions.sendRedirect("/logout");
    }

//    public AppUser whoami() {
//        return userService.whoami();
//    }

}
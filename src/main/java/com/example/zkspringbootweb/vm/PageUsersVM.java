package com.example.zkspringbootweb.vm;

import com.example.zkspringbootweb.entity.AppUser;
import io.ebean.DB;
import io.ebean.ExpressionList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;

@Data
@NoArgsConstructor
@VariableResolver(DelegatingVariableResolver.class)
public class PageUsersVM {

    @Wire
    Listbox usersGrid;

    PagingListModel<AppUser> myUserListModel;

    String usernameFilter = "";

    AppUser selectedUser;


    @AfterCompose
    public void init(@ContextParam(ContextType.VIEW) final Component view) {
        Selectors.wireComponents(view, this, false);
        usersGrid.getPagingChild().setMold("os");
        changeFilter();
    }

    @Command
    @NotifyChange({"myUserListModel"})
    public void changeFilter() {
        ExpressionList<AppUser> expr = DB.find(AppUser.class)
                .where()
                .contains("username", usernameFilter);


        myUserListModel = new PagingListModel<>(usersGrid.getPageSize(), () -> expr);
    }

    @Command
    @NotifyChange({"myUserListModel","selectedUser"})
    public void delete(@BindingParam("user") AppUser user) {
        DB.delete(user);
        changeFilter();
        selectedUser = null;
        Toast.show("Deleted successfully.", "error", "top_center");

    }

    @Command
    @NotifyChange({"*"})
    public void update(@BindingParam("user") AppUser user) {
        selectedUser.update();
    }

    @GlobalCommand
    @NotifyChange({"*"})
    public void refresh() {
        changeFilter();
    }


    @Command
    public void showAddUserForm() {
        Executions.createComponents("~./zul/app/popup/pop_user.zul", null, null);
    }
}

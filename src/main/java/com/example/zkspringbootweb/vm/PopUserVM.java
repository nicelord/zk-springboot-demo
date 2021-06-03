package com.example.zkspringbootweb.vm;

import com.example.zkspringbootweb.entity.AppUser;
import io.ebean.DB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author asus
 */

@Getter
@NoArgsConstructor
public class PopUserVM {

    @Wire("#pop_user")
    private Window win;

    private AppUser appUser;


    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view) {
        this.appUser = new AppUser();
        Selectors.wireComponents(view, this, false);
    }

    @Command
    public void save() {
        try {
            DB.save(this.appUser);
            BindUtils.postGlobalCommand(null, null, "refresh", null);
            this.win.detach();
            Toast.show("Saved successfully.");
        } catch (Exception e) {
            Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
        }
    }


}

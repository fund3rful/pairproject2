package userinterface;

import impresario.IModel;

//==============================================================================
public class ViewFactory {

    public static View createView(String viewName, IModel model) {
        if (viewName.equals("AccountView") == true) {
            return new AccountView(model);
        } else {
            return null;
        }
    }
}

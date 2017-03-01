package userinterface;

import impresario.IModel;

//==============================================================================
public class ViewFactory {

    public static View createView(String viewName, IModel model) {
        if (viewName.equals("PatronView") == true) {
            return new PatronView(model);
        }
        else if(viewName.equals("BookView")){
                return new BookView(model);
        } 
        else {
            return null;
           =
        }
    }
}

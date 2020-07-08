/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alert;

import alert.icons.IconImage;
import java.awt.Image;

/**
 *
 * @author Padovano
 */
public enum Alerttype {
    ERROR("error", IconImage.get("error")),
    WARNING("warning",IconImage.get("alert")),
    QUESTION("question",IconImage.get("question")),
    INFO("infos",IconImage.get("info")),
    SUCCESS("succes",IconImage.get("succes"));
//    MATERIAL_ERROR("error", IconImage.of("error")),
//    MATERIAL_WARNING("warning",IconImage.of("warning")),
//    MATERIAL_QUESTION("question",IconImage.of("question")),
//    MATERIAL_INFO("infos",IconImage.of("infos")),
//    MATERIAL_SUCCESS("success",IconImage.of("succes"));
    
    private String styleClass;
    private Image icon;
    
    Alerttype(String classStyle, Image icon){
        this.styleClass=classStyle;
        this.icon=icon;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public Image getIcon() {
        return icon;
    }
    public Image getIconFrom() {
        icon=IconImage.get(getStyleClass());
        return icon;
    }

    @Override
    public String toString() {
        return styleClass;
    }
    
}

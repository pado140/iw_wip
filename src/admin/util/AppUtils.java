/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import alert.Alert;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 * @author Padovano
 */
public class AppUtils {
   
    public static String convertToCode(String num,String Format){
        StringBuilder result=new StringBuilder();
        String prefix=Format.substring(0, Format.indexOf("#")-1).trim();
        String mask=Format.substring(Format.indexOf('#')).trim();
        result.append(prefix);
        result.append(" ");
        
        int len=mask.length();
        result.append(mask.substring(0,len-num.length()).replace("#", "0")).append(num);
        
        return result.toString();
    }
    

    public static void execMethod(Class<?> clazz,String methodName,boolean superclassMethod ,Object... parameters){
        Method m=null;
        boolean accessible=false;
        try {
            Class<?>[] paramtypes=new Class<?>[parameters.length];
            int i=0;
            for(Object o:parameters){
                paramtypes[i]=o.getClass();
                i++;
            }

            if(superclassMethod)
            m=clazz.getSuperclass().getDeclaredMethod(methodName,paramtypes);
            else
                m=clazz.getDeclaredMethod(methodName,paramtypes);
            if(m.isAccessible())
                accessible=true;
            
            if(!accessible)
                m.setAccessible(true);
            Object ob=clazz.newInstance();
            m.invoke(ob, parameters);
            if(!accessible)
                m.setAccessible(false);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
            Alert.error(null, ex.getMessage());
        }
    }
    public static Method loadMethod(Class<?> clazz,String methodName,Object... parameters){
         try {
            Method m=clazz.getDeclaredMethod(methodName);
            return m;
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException ex) {
            Alert.error(null, ex.getMessage());
        }
         return null;
    }

    public static String serializedParam(String deleimiter,String... param){
        return String.join(",",param);
    }

    public static Object[] queryParam(Map<String,Object> param){
        Object[] cle=new Object[0];
        if(param!=null)
            if(!param.isEmpty()) {
                cle = new Object[param.size()];
                int i = 0;
                for (String key : param.keySet()) {
                    cle[i] = param.get(key);
                    i++;
                }
            }
        return cle;
    }

    public static String serializedParam(Map<String,Object> param){
        String[] cle=new String[0];
        if(param!=null)
            if(!param.isEmpty()) {
                cle = new String[param.size()];
                int i = 0;
                for (String key : param.keySet()) {
                    cle[i] = key + "=?";
                    i++;
                }
            }
        return serializedParam("and",cle);
    }

    public static String[] PreparedExpression(Set<String> param){
        String[] cle=new String[0];
        if(param!=null)
            if(!param.isEmpty()) {
                cle = new String[param.size()];
                int i = 0;
                for (String key : param) {
                    cle[i] = key + "=?";
                    i++;
                }
            }
        return cle;
    }
    //
    
    public static boolean validate(String Value,InputValidation regex){
        Pattern pattern=Pattern.compile(regex.getRegex(),Pattern.CASE_INSENSITIVE );
        Matcher match=pattern.matcher(Value);
        return match.matches();
    }
    
//    
//    public static boolean valid(TextInputControl field){
//        System.out.println("validation:"+field);
//        field.setTooltip(null);
//            if(field.getText().trim().isEmpty()){
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//            }else{
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                return true;
//            }
//        Tooltip tooltip=new Tooltip("Veuiller remplir ce champ");
//        tooltip.setStyle("-fx-background-color:-color-red;-fx-text-fill:-color-white;");
//        field.setTooltip(tooltip);
//        return false;
//                //Tomorrow modified css to add pseudo class like valid and invalid for class .text-field
//    }
//    public static boolean valid(TextInputControl field,TextInputControl field2){
//        boolean check=false;
//        if(valid(field)&&valid(field2))
//            check=field.getText().equals(field2.getText());
//        if(!check){
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//                field2.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field2.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//            }else{
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                field2.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field2.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                return true;
//            }
//            Tooltip tooltip=new Tooltip("les deux champs doivent etre identiques");
//        tooltip.setStyle("-fx-background-color:-color-red;-fx-text-fill:-color-white;");
//        field.setTooltip(tooltip);
//        field2.setTooltip(tooltip);
//        
//        return check;
//                //Tomorrow modified css to add pseudo class like valid and invalid for class .text-field
//    }
//    public static boolean valid(TextInputControl field,int lon){
//        boolean check=valid(field)&&field.getText().trim().length()>=lon;
//        if(!check){
//            field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//            }else{
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                return true;
//            }
//            Tooltip tooltip=new Tooltip(String.format("Ce champ doit avoir au minimum %d caracteres",lon));
//        tooltip.setStyle("-fx-background-color:-color-red;-fx-text-fill:-color-white;");
//        field.setTooltip(tooltip);
//        
//        return check;
//    }
//    public static boolean valid(TextInputControl field,InputValidation regex){
//        boolean check=valid(field)&&validate(field.getText().trim(),regex);
//        if(!check){
//            field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//            }else{
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                return true;
//            }
//            Tooltip tooltip=new Tooltip(String.format("Ce champ doit contenir que des chiffres"));
//        tooltip.setStyle("-fx-background-color:-color-red;-fx-text-fill:-color-white;");
//        field.setTooltip(tooltip);
//
//        return check;
//    }
//    public static boolean valid(ComboBoxBase field){
//        System.out.println("validation:"+field);
//            if(field.getValue()==null){
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),true);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),false);
//            }else{
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"),false);
//                field.pseudoClassStateChanged(PseudoClass.getPseudoClass("valid"),true);
//                return true;
//            }
//            Tooltip tooltip=new Tooltip("vous devez faire un choix");
//        tooltip.setStyle("-fx-background-color:-color-red;-fx-text-fill:-color-white;");
//        field.setTooltip(tooltip);
//        return false;
//    }
//
//    public static boolean validation (AnchorPane block){
//        ObservableList<Node> input=block.getChildren();
//        boolean valid=true;
//        for(Node n:input){
//            if(n instanceof TextInputControl){
//                if(!valid((TextInputControl)n))
//                    valid=false;
//            }
//            if(n instanceof ComboBoxBase)
//            {
//                if(!valid((ComboBoxBase)n)){
//                    valid=false;
//                }
//            }
//        }
//        return valid;
//    }

     public static String ucfirst(String a){
         System.out.println(a.toUpperCase().charAt(0)+a.substring(1));
        return a.toUpperCase().charAt(0)+a.substring(1);
     }
    public static String getText(JTextComponent control){
        return control.getText().trim();
    }
    
    public static Object getValue(JComboBox control){
        return control.getSelectedItem();
    }

    public static void hydrated(Object objet,String name,Object value){
        Class ob=objet.getClass();

        String prefix="set";
        String methodname=prefix+Capitalize(name);
        System.out.println(methodname);
        System.out.println(ob.getName()+" :"+(objet==null));
        Method method;
        try {
            if(name.contains("_")){

                method=ob.getMethod("get"+Capitalize(name.split("_")[0]));
                Object cla=method.invoke(objet);
                Class obj=null;
                if(cla==null){

                    obj=Class.forName(ob.getPackage().getName()+"."+Capitalize(name.split("_")[0]));
                    cla=obj.getDeclaredConstructor().newInstance();
                }else
                    obj=cla.getClass();
                methodname=prefix+Capitalize(name.split("_")[1]);
                method=obj.getMethod(methodname,value.getClass().getCanonicalName().contains("Integer")?int.class:(value.getClass().getCanonicalName().contains("java.sql.Date")? LocalDate.class:(value.getClass().getCanonicalName().contains("java.sql.Timestamp")? LocalDateTime.class:value.getClass())));
                method.invoke(cla,value.getClass().getCanonicalName().contains("java.sql.Date")? ((java.sql.Date)value).toLocalDate():(value.getClass().getCanonicalName().contains("java.sql.Timestamp")? ((java.sql.Timestamp)value).toLocalDateTime():value));
                methodname=prefix+Capitalize(name.split("_")[0]);
                value=cla;
            }


//            method=ob.getMethod(methodname,value.getClass().getCanonicalName().contains("Integer")?int.class:(value.getClass().getCanonicalName().contains("java.sql.Date")? LocalDate.class:value.getClass()));
//            method.invoke(objet,value.getClass().getCanonicalName().contains("java.sql.Date")? ((java.sql.Date)value).toLocalDate():value);
            method=ob.getMethod(methodname,value.getClass().getCanonicalName().contains("Integer")?int.class:(value.getClass().getCanonicalName().contains("java.sql.Date")? LocalDate.class:(value.getClass().getCanonicalName().contains("java.sql.Timestamp")? LocalDateTime.class:value.getClass())));
            method.invoke(objet,value.getClass().getCanonicalName().contains("java.sql.Date")? ((java.sql.Date)value).toLocalDate():(value.getClass().getCanonicalName().contains("java.sql.Timestamp")? ((java.sql.Timestamp)value).toLocalDateTime():value));
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException | ClassNotFoundException | InstantiationException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }


    public static void serilized(Object objet,String tablename,Object value){
        Class ob=objet.getClass();

        String prefix="set";
        String methodname=prefix+Capitalize(tablename);
        Method method;
        try {

            method=ob.getMethod(methodname,value.getClass().getCanonicalName().contains("Integer")?int.class:(value.getClass().getCanonicalName().contains("java.sql.Date")? LocalDate.class:value.getClass()));
            method.invoke(objet,value.getClass().getCanonicalName().contains("java.sql.Date")? ((java.sql.Date)value).toLocalDate():value);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException  ex) {
//            AppLog.Log(AppUtils.class.getName(),Level.SEVERE,ex.getMessage(),ex);
        }


    }

    private static String Capitalize(String string){
        String text=string.trim().toLowerCase();
        String first=text.charAt(0)+"";
        StringBuilder st=new StringBuilder();
        st.append(first.toUpperCase());
        st.append(text.substring(1));
        return st.toString();
    }

    public static Map<String,Object> transform(Object o){
        Class clazz=o.getClass();
        Field[] fields=clazz.getDeclaredFields();
        Map<String,Object> data=new HashMap<>();
        for (Field f:fields) {
            try {
                String methodName="get"+ucfirst(f.getName());
                Method m=clazz.getMethod(methodName);

                data.put(f.getName(),m.invoke(o));
                System.out.println(m.invoke(o));
            } catch (IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        System.out.println(data);
        return null;
    }
}

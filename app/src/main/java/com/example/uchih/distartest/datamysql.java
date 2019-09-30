package com.example.uchih.distartest;

import java.util.ArrayList;

public class datamysql {

        public  static  String productid= "";
        public  static  String image_g= "";
        public  static  String name_g= "";
        public  static  String detail_g= "";
        public  static  String location_g= "";
        public  static  String pricr="";


        public static String language = "zh_CN";
        public  static ArrayList<String> list_cart;
        public  static ArrayList<Integer> list_number;
        public  static ArrayList<String> list_name;
        public  static  ArrayList<String> list_image;
        public  static  ArrayList<String> list_productid;

    public  static  ArrayList<String> list_cart_image;
    public  static  ArrayList<String> list_cart_proid;
    public static  ArrayList<String> list_cart_name;
    public static  ArrayList<String> list_cart_price;
    public static  ArrayList<Boolean> list_cart_check;

    public  static String Search="";

    public static ArrayList<String> getList_cart_image() {
        return list_cart_image;
    }

    public static void setList_cart_image(ArrayList<String> list_cart_image) {
        datamysql.list_cart_image = list_cart_image;
    }

    public static ArrayList<String> getList_cart_proid() {
        return list_cart_proid;
    }

    public static void setList_cart_proid(ArrayList<String> list_cart_proid) {
        datamysql.list_cart_proid = list_cart_proid;
    }

    public static ArrayList<String> getList_cart_name() {
        return list_cart_name;
    }

    public static void setList_cart_name(ArrayList<String> list_cart_name) {
        datamysql.list_cart_name = list_cart_name;
    }

    public  static  String cart_image= "";
    public  static  String cart_proid= "";
    public  static  String cart_name="";

   public  static  boolean arrayck=true;

   //cart
    public  static  String name_c= "";
    public  static  String price_c= "";
    public  static  String location_c="";
    public  static  String tel_c="";
    public  static  String email_c="";

    public static int check_vdo_on = 1;
    public  static  String vdoview="";
    public  static   int check_bank =0;
    public  static   String image_wechat ="";
    public  static  int numcart_=0 ;

    public static  int langued= 2;
}

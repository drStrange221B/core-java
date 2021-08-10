package com.company.emrys.corejava;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));

        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");
        String osArchitecture = System.getProperty("os.arch");
        String javaVendor = System.getProperty("java.vendor");

        System.out.println("userName: " + userName );
        System.out.println("userHome: " + userHome );
        System.out.println("osArchitecture: " + osArchitecture );
        System.out.println("javaVendor: " + javaVendor );

        Map<String, String> map = System.getenv();

        System.out.println(map.size());
        System.out.println(map);
    }
}

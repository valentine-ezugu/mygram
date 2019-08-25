package com.valentine.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpLocator {

    public static String getAddress() throws UnknownHostException {
      return InetAddress.getLocalHost().getHostAddress();
    }

}

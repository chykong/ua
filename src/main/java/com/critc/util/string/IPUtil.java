package com.critc.util.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * 系统信息工具类
 *
 * @author 孔垂云
 */
public final class IPUtil {

    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    /**
     * 取到当前机器的IP地址
     *
     * @return
     * @author piaohailin
     * @date 2013-5-6
     */
    public static String getIp() {
        String hostIp = null;
        if (hostIp == null) {
            List<String> ips = new ArrayList<String>();
            Enumeration<NetworkInterface> netInterfaces = null;
            try {
                netInterfaces = NetworkInterface.getNetworkInterfaces();
                while (netInterfaces.hasMoreElements()) {
                    NetworkInterface netInterface = netInterfaces.nextElement();
                    Enumeration<InetAddress> inteAddresses = netInterface.getInetAddresses();
                    while (inteAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inteAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                            ips.add(inetAddress.getHostAddress());
                        }
                    }
                }
            } catch (SocketException ex) {
                ex.printStackTrace();
            }
            hostIp = collectionToDelimitedString(ips, ",");
        }
        return hostIp;
    }

    private static String collectionToDelimitedString(Collection<String> coll, String delim) {
        if (coll == null || coll.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = coll.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * 获取服务器名称
     *
     * @return
     */
    public static String getHostName() {
        String hostName = null;
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return hostName;
    }
}

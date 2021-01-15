package com.sgai.pox.engine.core.session;

/**
 * @Auther: pox
 */
public class AssertContext {


    private static ThreadLocal<AcctSession> allContext = new ThreadLocal<AcctSession>();

    public static void init(AcctSession acctSession) {
        allContext.set(acctSession);
    }

    public static AcctSession get() {
        return allContext.get();
    }


    public static String getUserId() {
        return get().getUserId();
    }

    public final static void destroy() {
        allContext.remove();
    }

}

package ae.gov.sdg.paperless.platform.common.model;


public enum LOGIN_SOURCE {
    MPAY("mpu"),
    UAEPASS("aep");

    String val;

    LOGIN_SOURCE(final String type) {
        this.val = type;
    }

    public String getVal() {
        return val;
    }

    public static LOGIN_SOURCE getLoginSource(final String source) {
        for (final LOGIN_SOURCE type :
                LOGIN_SOURCE.values()) {
            if (type.getVal().equals(source)) {
                return type;
            }
        }
        return null;
    }
}

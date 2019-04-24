class Application {
    void start() {
        Pojo pojo = new Pojo();
        if (pojo.getS() != null) {
            System.out.println(pojo.getS().length());
        }
        try {
            pojo.hangOn();
        } catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}

class Pojo {
    private String s;

    String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    void hangOn() throws InterruptedException {
        Thread.sleep(1_000);
    }
}

public class Configuration {
    private int samplesize;
    private int objects;
    private Object pojo;
    private String useCase;

    public int getObjects() {
        return objects;
    }

    public void setObjects(int objects) {
        this.objects = objects;
    }

    public Object getPojo() {
        return pojo;
    }

    public void setPojo(Object pojo) {
        this.pojo = pojo;
    }

    public int getSamplesize() {
        return samplesize;
    }

    public void setSamplesize(int samplesize) {
        this.samplesize = samplesize;
    }

    public String getUseCase() {
        return useCase;
    }

    public void setUseCase(String useCase) {
        this.useCase = useCase;
    }
}

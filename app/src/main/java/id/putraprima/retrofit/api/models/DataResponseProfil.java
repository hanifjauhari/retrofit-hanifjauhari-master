package id.putraprima.retrofit.api.models;

public class DataResponseProfil {
    private DataProfil data;

    public DataResponseProfil(DataProfil data) {
        this.data = data;
    }

    public DataProfil getData() {
        return data;
    }

    public void setData(DataProfil data) {
        this.data = data;
    }
}

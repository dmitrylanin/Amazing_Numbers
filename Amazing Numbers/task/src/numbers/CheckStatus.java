package numbers;

/*
    Служебный класс - оборачиваю в него результаты проверок
 */

public class CheckStatus {
    private boolean status;
    private String str;

    CheckStatus(boolean status, String str){
        this.status = status;
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public boolean getStatus() {
        return status;
    }
}

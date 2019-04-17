package cn.Ideal.demo.util;

public class ResponseEntity {
    private String Msg;
    private Integer status;
    private Object data;

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "Msg='" + Msg + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

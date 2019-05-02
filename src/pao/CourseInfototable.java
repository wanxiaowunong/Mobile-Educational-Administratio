package pao;

public class CourseInfototable {
    private String cname;
    private String cplace;
    private String ctname;
    private String zhoushu;
    private String jieshu;  //默认2
    private String time1;
    private String time2;
    private String xingqi;
    private String which;  //从哪节开始

    public CourseInfototable(String cname, String cplace, String ctname, String zhoushu, String jieshu, String time1, String time2, String xingqi, String which) {
        this.cname = cname;
        this.cplace = cplace;
        this.ctname = ctname;
        this.zhoushu = zhoushu;
        this.jieshu = jieshu;
        this.time1 = time1;
        this.time2 = time2;
        this.xingqi = xingqi;
        this.which = which;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCplace(String cplace) {
        this.cplace = cplace;
    }

    public void setCtname(String ctname) {
        this.ctname = ctname;
    }

    public void setZhoushu(String zhoushu) {
        this.zhoushu = zhoushu;
    }

    public void setJieshu(String jieshu) {
        this.jieshu = jieshu;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public void setXingqi(String xingqi) {
        this.xingqi = xingqi;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public String getCname() {

        return cname;
    }

    public String getCplace() {
        return cplace;
    }

    public String getCtname() {
        return ctname;
    }

    public String getZhoushu() {
        return zhoushu;
    }

    public String getJieshu() {
        return jieshu;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }

    public String getXingqi() {
        return xingqi;
    }

    public String getWhich() {
        return which;
    }
}

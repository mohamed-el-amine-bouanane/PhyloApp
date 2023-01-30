package Model2;


public class espece
{
    String val;
    public String name;
    String des;
    // public String code;
    espece(String v, String n, String d)
    {
        val=v;
        name=n;
        des=d;
        //  code=n.substring(0,4);
    }
    espece copy()
    {
        espece e= new espece(this.val,this.name,this.des);
        return e;
    }
    boolean compareto(espece e)
    {
        if((this.val==e.val)&&((this.name.equals(e.name))))
        {
            return true;
        }
        return false;
    }
}

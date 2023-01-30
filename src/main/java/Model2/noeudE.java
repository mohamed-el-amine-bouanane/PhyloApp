package Model2;

public class noeudE {
    public espece val;
    private noeudE fg;
    private noeudE fd;
    noeudE(espece v, noeudE g,noeudE d )
    {
        val=v;
        fg=g;
        fd=d;
    }
    espece getval()
    {
        return val;
    }
    public noeudE getfg()
    {
        return fg;
    }
    public noeudE getfd()
    {
        return fd;
    }
    void setval(espece v)
    {
        val=v;
    }
    void setfg(noeudE g)
    {
        fg=g;
    }
    void setfd(noeudE d)
    {
        fd=d;
    }
    public String toString()
    {
        String s=new String();
        s="le nom est: "+this.val.name+" la valeur est : "+this.val.val;
        return s;
    }

}

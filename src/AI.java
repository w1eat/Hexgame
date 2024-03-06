public class AI{
public void ai(Game g)
        {
        int maxx=0,maxy=0;
        double max=0;

        for(int i=0;i<11;i++)
        {
        for(int j=0;j<11;j++)
        {
        if(g.p[i][j]>=max&&g.board[i][j]==0){max=g.p[i][j];maxx=i;maxy=j;}
        }
        }
        if(maxx>0&&maxx<10&&maxy>0&&maxy<10){
        g.p[maxx-1][maxy+1]+=0.5;
        g.p[maxx+1][maxy-1]+=0.5;
        g.p[maxx][maxy+1]+=0.8;
        g.p[maxx][maxy-1]+=0.8;
        g.p[maxx+1][maxy]+=0.2;
        g.p[maxx-1][maxy]+=0.2;
        }
        else if(maxx==0&&maxy>0&&maxy<10){
        g.p[maxx+1][maxy]+=0.2;
        g.p[maxx+1][maxy-1]+=0.5;
        g.p[maxx][maxy-1]+=0.8;
        g.p[maxx][maxy+1]+=0.8;
        }
        else if(maxx==10&&maxy>0&&maxy<10){
        g.p[maxx-1][maxy]+=0.2;
        g.p[maxx-1][maxy+1]+=0.5;
        g.p[maxx][maxy-1]+=0.8;
        g.p[maxx][maxy+1]+=0.8;
        }
        else if(maxy==0&&maxx>0&&maxx<10){
        g.p[maxx-1][maxy+1]+=0.5;
        g.p[maxx-1][maxy]+=0.2;
        g.p[maxx+1][maxy]+=0.8;
        g.p[maxx][maxy+1]+=0.2;
        }
        else if(maxy==10&&maxx>0&&maxx<10){
        g.p[maxx-1][maxy]+=0.2;
        g.p[maxx+1][maxy]+=0.2;
        g.p[maxx+1][maxy-1]+=0.5;
        g.p[maxx][maxy-1]+=0.8;
        }
        else if(maxx==0&&maxy==0){
                g.p[maxx][maxy+1]+=0.2;
                g.p[maxx+1][maxy]+=0.8;
        }
        else if(maxx==10&&maxy==10){
                g.p[maxx][maxy-1]+=0.2;
                g.p[maxx-1][maxy]+=0.8;
        }
        else if(maxx==10&&maxy==0){
                g.p[maxx][maxy+1]+=0.2;
                g.p[maxx-1][maxy]+=0.8;
                g.p[maxx-1][maxy+1]+=0.3;
        }
        else if(maxx==0&&maxy==10){
                g.p[maxx][maxy-1]+=0.2;
                g.p[maxx+1][maxy]+=0.8;
                g.p[maxx+1][maxy-1]+=0.3;
        }
        g.board[maxx][maxy]=1;

        }
        }
import java.util.Arrays;

class Win {
    int a[]=new int[5100];
    int ra[]=new int[5100];
    int book[]=new int[5100];
    int rbook[]=new int[5100];

    int l[][]=new int[2000][2000];//蓝棋邻接矩阵
    int rl[][]=new int[2000][2000];//红棋
    int k=0;//蓝棋个数
    int rk=0;//红棋个数
    static boolean f=false;
    static boolean f2=false;
    static boolean f3=false;
    static boolean f4=false;
    public void juzhen(Game g){
        k=0;
        for(int i=0;i<2000;i++) {
            for (int j = 0; j < 2000; j++) {
                l[i][j]=0;
            }
        }
        for (int i = 0; i < 5100; i++) {
            book[i]=0;
        }
        for (int i = 0; i < 5100; i++) {
            a[i]=0;
        }
        for(int i=0;i<11;i++) {
            for (int j = 0; j < 11; j++) {
                if (g.board[i][j] == 2) a[k++] = j * 11 + i;
            }
        }
       //排序
       //边界条件，更改邻接矩阵
       priority(a,k,l);

    }//邻接矩阵
    public void rjuzhen(Game g){
        rk=0;
        for(int i=0;i<2000;i++) {
            for (int j = 0; j < 2000; j++) {
                rl[i][j]=0;
            }
        }
        for (int i = 0; i < 5100; i++) {
            rbook[i]=0;
        }
        for (int i = 0; i < 5100; i++) {
            ra[i]=0;
        }
        for(int i=0;i<11;i++) {
            for (int j = 0; j < 11; j++) {
                if (g.board[i][j] == 1) ra[rk++] = j * 11 + i;
            }
        }
        Arrays.sort(ra,0,rk);
        //边界条件，更改邻接矩阵
        priority(ra,rk,rl);

    }
    //深度优先算法
    public  void DFS(int v){
        if(a[v]%11==10)f=true;
                   book [v]=1;
                    for (int j = 0; j < k; j++) {
                        if(l[v][j]==1&&book[j]==0){
                            DFS(j);
                        }
                    }
    }
    public  void rDFS(int v){
        if(ra[v]/11==10)f2=true;
        rbook [v]=1;
        for (int j = 0; j < rk; j++) {
            if(rl[v][j]==1&&rbook[j]==0){
                rDFS(j);
            }
        }
    }

    public void priority(int _a[],int _k,int _l[][]){
        for(int i=0;i<_k;i++)
            for (int j = 0; j < _k; j++) {
                if(_a[i]/11!=0&&_a[i]/11!=10&&_a[i]%11!=0&&_a[i]%11!=10) {
                    if (_a[j] == _a[i] - 1 || _a[j] == _a[i] + 1 || _a[j] == _a[i] - 11 || _a[j] == _a[i] + 11 || _a[j] == _a[i] - 11 + 1 || _a[j] == _a[i] + 11 - 1)
                        _l[i][j] = 1;
                    else _l[i][j] = 0;
                }
                if(_a[i]%11==0&&_a[i]/11!=10&&_a[i]/11!=0){
                    if(_a[j] == _a[i] + 1  || _a[j] == _a[i] - 11 || _a[j] == _a[i] + 11 || _a[j] == _a[i] - 11 + 1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]%11==10&&_a[i]/11!=10&&_a[i]/11!=0){
                    if(_a[j] == _a[i] - 1  || _a[j] == _a[i] - 11 || _a[j] == _a[i] + 11 ||  _a[j] == _a[i] + 11 - 1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]/11==0&&_a[i]%11!=0&&_a[i]%11!=10){
                    if(_a[j] == _a[i] - 1 || _a[j] == _a[i] + 1  || _a[j] == _a[i] + 11 ||  _a[j] == _a[i] + 11 - 1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]/11==10&&_a[i]%11!=0&&_a[i]%11!=10){
                    if(_a[j] == _a[i] - 1 || _a[j] == _a[i] + 1  || _a[j] == _a[i] - 11 ||  _a[j] == _a[i] - 11 + 1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]==0){
                    if (_a[j] == _a[i] + 1  || _a[j] == _a[i] +11  )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]==10){
                    if (_a[j] == _a[i] - 1  || _a[j] == _a[i] +11 ||_a[j]==_a[i]+11-1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]==110){
                    if (_a[j] == _a[i] + 1  || _a[j] == _a[i] -11 ||_a[j]==_a[i]-11+1 )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
                if(_a[i]==120){
                    if (_a[j] == _a[i] - 1  || _a[j] == _a[i] -11  )
                        _l[i][j]=1;
                    else _l[i][j]=0;
                }
            }
    }
}
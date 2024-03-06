import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.*;

public class Game extends JFrame {
    private JFrame f=new JFrame("海克斯棋游戏");
    //声明四个对象记录图片
    BufferedImage table;
    BufferedImage blue;
    BufferedImage red;
    BufferedImage selected;
    //棋盘宽高
    final int TABLE_WIDTH=590;
    final int TABLE_HEIGHT=366;
    //棋盘下子
    final int BOARD_SIZE=11;
    //棋子占用棋盘比率
    final  int RATE_X=TABLE_WIDTH/16;
    final int RATE_Y=TABLE_HEIGHT/11-1;
    //声明变量，记录棋子对于x和y方向的偏移量
    final int X_OFFSET=5;
    final  int Y_OFFSET=1;
    //棋子矩阵
    int [][] board=new int[BOARD_SIZE][BOARD_SIZE];
    double [][]p = new double[11][11];
    //声明红色选择框
    int selected_X=-1;
    int selected_Y=-1;
    //自定义类继承Canvas绘图
    private class ChessBoard extends JPanel{
        public void paint(Graphics g){
            //绘制棋盘
            g.drawImage(table,0,0,null);
            if(selected_X>=0&&selected_Y>=0) {
                g.drawImage(selected, selected_X*RATE_X + X_OFFSET+selected_Y*18, selected_Y*RATE_Y + Y_OFFSET, null);
            }
//判断绘图棋子类型
            for (int i = 0; i < BOARD_SIZE; i++)
            {
                for (int j = 0; j < BOARD_SIZE; j++)
                {
                    int x = i * RATE_X + X_OFFSET + j * 18;
                    if(board[i][j]==2)
                    {
                        g.drawImage(blue, x,j*RATE_Y+Y_OFFSET,null);

                    }
                    if(board[i][j]==1)
                    {
                        g.drawImage(red, x,j*RATE_Y+Y_OFFSET,null);

                    }
                }
            }
        }
    }
    ChessBoard chessBoard=new ChessBoard();
    AI a=new AI();
    int board_type=2;//临时变量
    int bo=1;//判断下一个棋子类型
Win w=new Win();
    public void init() throws Exception{
        //组装棋盘

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                p[i][j]=2;
            }
        }
        //p[3][7]=6;//初始化图片
        board[7][3]=1;
        table= ImageIO.read(new File("img\\board.jpg"));
        red= ImageIO.read(new File("img\\red.png"));
        blue= ImageIO.read(new File("img\\blue.png"));
        selected= ImageIO.read(new File("img\\selected.png"));
        //处理鼠标移动显示选择框
        chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                selected_Y=(e.getY()-Y_OFFSET)/RATE_Y;
                selected_X=(e.getX()-18*selected_Y)/RATE_X;
                chessBoard.repaint();

            }
        });
        //鼠标点击绘制棋子

        chessBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if(bo%2==1)board_type=2;
                //else if(bo%2==0) board_type=1;
                //bo++;
                  getai(e);
                 w.juzhen(Game.this);//初始化邻接矩阵
                for (int i = 0; i < 11; i++) {
                    if(board[0][i]==2){
                        int j;
                        for ( j = 0; j < w.k; j++) {
                            if(w.a[j]==i*11)break;
                        }
                        w.DFS(j);
                    }
                    if(w.f) {
                        System.out.print("你赢了");  showJDialog("你赢麻了！！！！！！！！");
                        break;
                    }
                }//判断胜负

                chessBoard.repaint();
                //判断电脑胜利
                w.rjuzhen(Game.this);
                for (int i = 0; i < 11; i++) {
                    if(board[i][0]==1){
                        int j;
                        for ( j = 0; j < w.rk; j++) {
                            if(w.ra[j]==i)break;
                        }
                        w.rDFS(j);
                    }
                    if(w.f2) {
                        System.out.print("你输了");  showJDialog("电脑玩家获胜！");
                        break;
                    }
                }
            }
            public void mouseExited(MouseEvent e) {
                selected_X=-1;
                selected_Y=-1;
                chessBoard.repaint();
            }
        });
        chessBoard.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
        f.add(chessBoard);
        //设置frame大小
        f.pack();
        f.setVisible(true);
    }
    public void getai(MouseEvent e) {//落子改变优先级
        board_type = 2;
        int Ypos = (e.getY() - Y_OFFSET) / RATE_Y;
        int Xpos = (e.getX() - 18 * Ypos) / RATE_X;
        if (board[Xpos][Ypos] == 0 && Ypos < 11 && Xpos < 11) {
            board[Xpos][Ypos] = board_type;
            if (Xpos > 0 && Xpos < 10 && Ypos > 0 && Ypos < 10) {
                p[Xpos - 1][Ypos] += 2;
                p[Xpos + 1][Ypos] += 2;
                p[Xpos - 1][Ypos + 1] -= 0.5;
                p[Xpos + 1][Ypos - 1] -= 0.5;
                p[Xpos][Ypos - 1] -= 0.8;
                p[Xpos][Ypos + 1] -= 0.8;
                /*if(Xpos > 1 && Xpos < 9 && Ypos > 1 && Ypos < 9) {
                    if (board[Ypos + 1][Xpos - 1] == 2 && board[Ypos + 2][Xpos - 2] == 2 ) {
                        p[Ypos - 1][Xpos + 1] += 5;
                    }
                    if (board[Ypos - 1] [Xpos + 1]== 2 && board[Ypos - 2][Xpos + 2] == 2) {
                        p[Ypos + 1][Xpos - 1] += 5;
                    }
                }*/
            } else if (Xpos == 0 && Ypos > 0 && Ypos < 10) {
                p[Xpos + 1][Ypos] += 2;
                p[Xpos + 1][Ypos - 1] -= 0.8;
                p[Xpos][Ypos - 1] -= 0.5;
                p[Xpos][Ypos + 1] -= 0.5;
            } else if (Xpos == 10 && Ypos > 0 && Ypos < 10) {
                p[Xpos - 1][Ypos] += 2;
                p[Xpos - 1][Ypos + 1] -= 0.8;
                p[Xpos][Ypos - 1] -= 0.5;
                p[Xpos][Ypos + 1] -= 0.5;
            } else if (Ypos == 0 && Xpos > 0 && Xpos < 10) {
                p[Xpos - 1][Ypos] += 2;
                p[Xpos + 1][Ypos] += 2;
                p[Xpos - 1][Ypos + 1] += 0.5;
                p[Xpos][Ypos + 1] -= 0.5;
            } else if (Ypos == 10 && Xpos > 0 && Xpos < 10) {
                p[Xpos - 1][Ypos] += 2;
                p[Xpos + 1][Ypos] += 2;
                p[Xpos + 1][Ypos - 1] += 0.5;
                p[Xpos][Ypos - 1] -= 0.5;
            }
            if (Xpos==0&&Ypos==10)p[10][0]+=6;
            if (Ypos==0&&Xpos==10)p[0][10]+=6;
            a.ai(Game.this);
        }
    }
    //胜利弹窗
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
}

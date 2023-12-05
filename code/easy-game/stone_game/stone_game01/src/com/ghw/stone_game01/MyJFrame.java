package com.ghw.stone_game01;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Random;

public class MyJFrame extends JFrame implements KeyListener {
    int[][] data = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int row, col;
    int count;
    long starttime, endtime;
    long min_time = 3600;
    double timeuser;

    /**
     * 构造方法 ——————————只要创建对象就会执行
     */
    public MyJFrame()
    {
        //注册键盘监听
        this.addKeyListener(this);
        //初始化窗体
        initFrame();
        //初始化数据（打乱石头顺序）
        initdata();
        //绘制游戏界面
        paintview();
        //窗体可视化
        setVisible(true);
    }

    /**
     * 初始化数据（打乱石头顺序）
     */
    public void initdata()
    {
        starttime = Calendar.getInstance().getTimeInMillis();
        Random r = new Random();
        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[i].length; j++)
            {
                int randomx = r.nextInt(4);
                int randomy  =r.nextInt(4);
                int ans = data[i][j];
                data[i][j] = data[randomx][randomy];
                data[randomx][randomy] = ans;
            }
        }
        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++)
                if(data[i][j] == 0)
                {
                    row = i;
                    col = j;
                }
    }
    /**初始化窗体*/
    public void initFrame()
    {
        //设置关闭格式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置窗体大小
        setSize(514, 595);
        //加上标题
        setTitle("石头迷阵单机版 v1.0");
        //设置窗体位置
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        //取消组件的默认放置
        setLayout(null);
    }

    //判断是否胜利
    public boolean victory()
    {
        for (int i = 0; i < win.length; i++)
            for (int j = 0; j < win[i].length; j++)
            {
                if(win[i][j] != data[i][j])
                    return false;
            }
        return true;
    }

    /**
     * 绘制游戏界面
     */
    public void paintview()
    {
        //移除所有组件，进行覆盖
        getContentPane().removeAll();

        if(victory())
        {
            endtime = Calendar.getInstance().getTimeInMillis();
            timeuser = (endtime - starttime) / 1000;
            JLabel time_use = new JLabel("消耗时间为" + timeuser + "s");
            time_use.setBounds(200, 20, 100, 20);
            getContentPane().add(time_use);
            if(timeuser < min_time)
            {
                min_time = (long) timeuser;
                JLabel time_use_min = new JLabel("恭喜你打破记录！！！");
                time_use_min.setBounds(175, 0, 150, 20);
                getContentPane().add(time_use_min);
            }
            JLabel win = new JLabel(new ImageIcon("D:\\jcode\\javacode\\stone_game\\image\\win.png"));
            win.setBounds(124, 230, 266, 88);
            getContentPane().add(win);
        }
        for (int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                JLabel imagLabel = new JLabel(new ImageIcon("D:\\jcode\\javacode\\stone_game\\image\\" + data[i][j] + ".png"));
                imagLabel.setBounds(50 + 100 * j, 90 + 100 * i, 100, 100);
                getContentPane().add(imagLabel);
            }
        }

        JButton bt = new JButton("重新游戏");
        bt.setBounds(350, 20, 100, 20);
        getContentPane().add(bt);
        //取消焦点
        bt.setFocusable(false);
        bt.addActionListener(e -> {
            timeuser = 0;
            count = 0;
            initdata();
            paintview();
        });

        JLabel scoreLabel = new JLabel("步数为" + count);
        scoreLabel.setBounds(50, 20, 100, 20);
        getContentPane().add(scoreLabel);

        JLabel background = new JLabel(new ImageIcon("D:\\jcode\\javacode\\stone_game\\image\\background.png"));
        background.setBounds(26, 30, 450, 484);
        getContentPane().add(background);

        //刷新界面
        getContentPane().repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        move(keycode);
        //每次移动后都更新游戏界面
        paintview();
    }
    //处理移动业务
    private void move(int keycode) {

        if(victory())
            return ;
        if(keycode == 37)
        {
            if(col == 3)
                return;
            data[row][col] = data[row][col + 1];
            data[row][col + 1] = 0;
            col++;
            count++;
        }
        else if(keycode == 38)
        {
            if(row == 3)
                return ;
            data[row][col] = data[row + 1][col];
            data[row + 1][col] = 0;
            row++;
            count++;
        }
        else if(keycode == 39)
        {
            if(col == 0)
                return ;
            data[row][col] = data[row][col - 1];
            data[row][col - 1] = 0;
            col--;
            count++;
        }
        else if(keycode == 40)
        {
            if(row == 0)
                return ;
            data[row][col] = data[row - 1][col];
            data[row - 1][col] = 0;
            row--;
            count++;
        }
        else if(keycode == 90)
        {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
        }
    }
    //-------------------------------------------------
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
//----------------------------------------------------

}

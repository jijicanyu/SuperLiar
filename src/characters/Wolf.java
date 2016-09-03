package characters;

import RuleAlgorithm.Alive;
import act.Kill;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by geyao on 16/7/5.
 */
public class Wolf extends Village implements Kill{

    private int choice = 0;

    public Wolf( ){ // 构造函数
        super();
        this.setIdentity(2); // 2为狼人
//        ArrayList<Integer> wolves = new ArrayList<>();
//        for (int i : Alive.Wolves.keySet()) {
//            wolves.add(i);
//        }
//        this.lightOthersButton(Alive.intWolves, Color.RED); // 将队友设置为红色
    }

    public int wolfkill( ArrayList<Integer> alivePlayers, ArrayList<Integer> WfKill){ // 夜晚击杀函数, 参数是当前存活玩家的 座次数组
        sendMessage(this.getNumber()+"号玩家, 你的身份是狼人, 狼阵营的玩家有\n"+ Alive.Wolves.keySet()+'\n');
        sendMessage(alivePlayers+"可杀,请输入欲击杀玩家座次号");
        choice = -1; // 将choice置 -1

        int choiceKill = input(alivePlayers);
//        Clock clock = new Clock();    计时器以后再做吧
//        clock.timeBegin(this,30);
//        do{
//            choiceKill = in.nextInt();
//        }
//        while ( confirmTarget( alivePlayers, choiceKill) == -1 && choice == -1 );
//        clock.c
        if (alivePlayers.contains(choiceKill)) {
            this.choice = choiceKill;
            WfKill.add(choice);
            return this.getChoice();
        }
        else return -1;
    }

    @Override
    public void night(){ // 重写狼函数, 狼人夜晚只等刀人投票而已
        while (Alive.nightKey) {
            while (Alive.voteKey[getNumber()]) { // 可以投票了
                jTextArea.append("\n狼人, 请选择击杀目标\n");


                int res = input(); // 夜晚投票, 候选人即全部存活玩家
//                Alive.candidates.get(this.getNumber()).clear(); // 投票完清空候选人列表
                if (res >= 0) { // 未弃票
                    Alive.voteResult[res]++; // 被投玩家加一票
                }
                Alive.voteKey[this.getNumber()] = false; // 已经投票完了
            }
        }
    }

    public int getChoice(){ // 查询击杀目标
        return choice;
    }

    public void clearChoice(){ // 重置击杀目标为0
        choice = 0;
    }

    public int kill( Village village){
        if(village.getalive()){
            village.die(1);
            return 1;
        }
        else
            return 0;
    }

    public String print( String god ){
        super.print();
        System.out.println("该玩家是狼人");
        return "该玩家是狼人";
    }

    private int confirmTarget( ArrayList<Integer> alivePlayers, int target){ // 确定数组里是否有目标元素
        for( int a: alivePlayers ){
            if (a == target)
                return a;
        }
        return 0;
    }

}

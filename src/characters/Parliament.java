package characters;

import act.Kill;

import java.util.ArrayList;

/**
 * Created by geyao on 16/7/7.
 */
public class Parliament implements Kill{

    private int target = 0;
    private ArrayList<Integer> voters = new ArrayList<>();

    public Parliament( int target ){ // 议会构造函数
        this.target = target;
    }

    public int  getTarget(){ // 查询票选目标
        return this.target;
    }

    public void addVoter(Village village){ // 有新的投 目标玩家 的 投票者
        voters.add( village.getNumber() );
    }

    public int kill( Village village){
        village.die(2);
        return 3;
    }
}

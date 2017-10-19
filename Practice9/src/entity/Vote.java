package entity;

import java.util.ArrayList;
import java.util.List;

public class Vote {
    private int count = 0;
    private List<String> voters = new ArrayList<String>();

    public void vote(String voter) {
        count++;
        voters.add(voter);
    }

    public int getCount() {
        return count;
    }

    public List<String> getVoters() {
        return voters;
    }
}

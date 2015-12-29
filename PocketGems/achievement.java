interface requirement{
	public boolean isSatisfied(Player player);
}

interface reward{
	public boolean isGiven(Player player);
}

class Player{
	int lvl;
}

class reachlvlreq implements requirement{
	int level;
	reachlvlreq(int x){
		this.level=x;
	}

	@Override
	public boolean isSatisfied(Player player){
		return player.lvl>=this.level;
	}
}


class Achievement{
	List<requirement> reqlist;
	List<reward> rewardlist;

	public Achievement(){
		this.reqlist=null;
		this.rewardlist=null;
	}

	
}
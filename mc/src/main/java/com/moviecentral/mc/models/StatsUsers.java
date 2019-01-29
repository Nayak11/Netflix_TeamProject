package com.moviecentral.mc.models;

import com.moviecentral.mc.entity.PlayHistory;

public class StatsUsers {
	PlayHistory phis;
	long cnt;
	public PlayHistory getPhis() {
		return phis;
	}
	public void setPhis(PlayHistory phis) {
		this.phis = phis;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}
	public StatsUsers(PlayHistory phis, long cnt) {
		super();
		this.phis = phis;
		this.cnt = cnt;
	}
	public StatsUsers() {
		this.phis = null;
		this.cnt = 0;
	}
	
}

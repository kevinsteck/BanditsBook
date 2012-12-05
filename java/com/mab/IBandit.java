package com.mab;

public interface IBandit {
	int select_arm();
	void update(int arm_index, double reward);
}

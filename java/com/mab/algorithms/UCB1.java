package com.mab.algorithms;

import com.mab.IBandit;

public class UCB1 implements IBandit {
	private int[] m_counts;
	private double[] m_values;
	
	public UCB1(int num_arms){
		m_counts = new int[num_arms];
		m_values = new double[num_arms];
	}
	
	@Override
	public int select_arm() {
		// cheap to do this now which *may* be used later
		int countsSum = 0;
		
		for(int arm : m_counts){
			if(arm == 0) return arm;
			countsSum += arm;
		}
		
		double max = 0;
		int max_indx = 0;
		for(int i = 0; i<m_counts.length; i++){
			double bonus = Math.sqrt((2 * Math.log(countsSum)) / m_counts[i]);
			double ucb_value = m_values[i] + bonus;
			if(ucb_value > max){ 
				max = ucb_value;
				max_indx = i;
			}
		}
		return max_indx;
	}

	@Override
	public void update(int arm_index, double reward) {
		int curCount = ++m_counts[arm_index];
		double curValue = m_values[arm_index];
		
		double newValue = ((curCount -1) / curCount) * curValue + (1 / curCount) * reward;
		m_values[arm_index] = newValue;
	}

}

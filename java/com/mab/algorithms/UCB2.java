package com.mab.algorithms;
import com.mab.IBandit;

public class UCB2 implements IBandit {
	private int[] m_counts;
	private double[] m_values;
	private double m_alpha;
	private int current_arm = 0;
	private int next_update = 0;
	
	// don't know what this does just yet
	private int[] m_r;
	
/*	public UCB2(int num_arms){
		m_counts = new int[num_arms];
		m_values = new double[num_arms];
		// don't know what this does just yet
		m_r = new int[num_arms]; 
	}*/
	
	public UCB2(int num_arms, double alpha, int[] counts, double[] values){
		// may have to do error checking here (if num_arms != counts.length/values.length/alpha.length
		// don't think i need num_arms
		m_alpha = alpha;
		m_counts = counts;
		m_values = values;
	}
	
	private double bonus(int n, int r){
		double tau = tau(r);
		double bonus = Math.sqrt((1 + m_alpha) * Math.log(Math.E * (n / tau)) / (2 * tau));
		
		return bonus;
	}
	
	private double tau(int r){
		return Math.ceil(Math.pow((1 + m_alpha),r));
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

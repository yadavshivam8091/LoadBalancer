package io.ys.services;


import org.json.JSONObject;
import org.springframework.stereotype.Service;
import io.ys.scheduling.LoadBalance;

@Service("loadbalancer.server")
public class Server {

	private static LoadBalance loadBalance= null; 
	private static LoadBalanceAdapter loadBalanceAdapter = null;

	public Server( LoadBalanceAdapter loadBalanceAdapter) {
		Server.loadBalanceAdapter= loadBalanceAdapter;
	}
	
	public JSONObject get(){
		
		return getLoadBalance().getServer();
	}
	
	LoadBalance getLoadBalance(){
		if(loadBalance != null) {
			return loadBalance;
		}
		return loadBalanceAdapter.get();
	}
}

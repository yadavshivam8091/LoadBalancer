package io.ys.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("loadbalancer.serveradapter")
public class ServerAdapter {

	private LoadBalanceAdapter loadBalanceAdapter;
	@Autowired
	public ServerAdapter(@Qualifier("loadbalancer.loadbalanceadapter") LoadBalanceAdapter loadBalanceAdapter) {
		this.loadBalanceAdapter = loadBalanceAdapter;
	}
	public Server getServer() {
		return new Server(loadBalanceAdapter);
	}
}

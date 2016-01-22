package com.mao.test;

import java.lang.reflect.Method;

import com.mao.android.proxy.ActivityManagerProxyer;
import com.mao.android.proxy.ActivityManagerProxyer.ActivityManagerInvocationHandler;
import com.mao.revelator.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * 测试Activity
 * 
 * @author mao
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void click(View v) {
		
		ActivityManagerProxyer.requestActivityManagerProxy(new ActivityManagerInvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Object iActivityManager, Method method, Object[] args) throws Throwable {
				System.out.println(method.getName());
				return method.invoke(iActivityManager, args);
			}
		});
	}

}

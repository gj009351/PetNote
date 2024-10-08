/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.duke.petnote.DEMO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.duke.petnote.R;
import com.duke.library.base.BaseActivity;
import com.duke.library.interfaces.OnBottomDragListener;
import com.duke.library.manager.TimeRefresher;
import com.duke.library.manager.TimeRefresher.OnTimeRefreshListener;
import com.duke.library.util.StringUtil;


/** 使用方法：复制>粘贴>改名>改代码 */
/**时间刷新器使用activity示例
 * @author Lemon
 * @use toActivity(DemoTimeRefresherActivity.createIntent(...));
 */
public class DemoTimeRefresherActivity extends BaseActivity
implements OnClickListener, OnBottomDragListener, OnTimeRefreshListener {
	private static final String TAG = "DemoTimeRefresherActivity";

	//启动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**启动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, DemoTimeRefresherActivity.class);
	}

	//启动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//demo_time_refresher_activity改为你所需要的layout文件；传this是为了全局滑动返回
		setContentView(R.layout.demo_time_refresher_activity, this);

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

	}


	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	//示例代码<<<<<<<<
	private TextView tvDemoTimeRefresherCount;
	private EditText etDemoTimeRefresher;
	//示例代码>>>>>>>>
	@Override
	public void initView() {//必须调用
		//示例代码<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		tvDemoTimeRefresherCount = findView(R.id.tvDemoTimeRefresherCount);
		etDemoTimeRefresher = findView(R.id.etDemoTimeRefresher);
		//示例代码>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	}

	private void clear() {
		TimeRefresher.getInstance().removeTimeRefreshListener(TAG);
		count = 0;
		tvDemoTimeRefresherCount.setText("0");
	}


	private boolean isToStop = false;
	private void stopOrContinu() {
		isToStop = !isToStop;
	}

	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须调用

	}


	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用
		//示例代码<<<<<<<<<<<<<<<<<<<
		tvDemoTimeRefresherCount.setOnClickListener(this);
		findView(R.id.ibtnDemoTimeRefresher).setOnClickListener(this);
		//示例代码>>>>>>>>>>>>>>>>>>>
	}

	@Override
	public void onTimerStart() {
		showShortToast("start");
	}
	private int count = 0;
	@Override
	public void onTimerRefresh() {
		if (isToStop == false) {
			count ++ ;
			tvDemoTimeRefresherCount.setText("" + count);
		}
	}
	@Override
	public void onTimerStop() {
		showShortToast("stop");
	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			clear();
			return;
		}

		finish();
	}



	//示例代码<<<<<<<<<<<<<<<<<<<
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.tvDemoTimeRefresherCount) {
			stopOrContinu();
		} else if (id == R.id.ibtnDemoTimeRefresher) {
			clear();
			isToStop = false;

			String number = StringUtil.getNumber(etDemoTimeRefresher);
			if (StringUtil.isNotEmpty(number, true)) {
				TimeRefresher.getInstance().addTimeRefreshListener(TAG
						, 0 + Integer.valueOf(number), this);
			}
		}
	}
	//示例代码>>>>>>>>>>>>>>>>>>>




	//生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	protected void onDestroy() {
		TimeRefresher.getInstance().removeTimeRefreshListener(TAG);
		super.onDestroy();
	}

	//生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>









	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
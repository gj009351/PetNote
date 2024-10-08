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

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import com.duke.petnote.R;
import com.duke.library.base.BaseTabFragment;
import com.duke.library.ui.PlacePickerWindow;
import com.duke.library.util.PlaceUtil;
import com.duke.library.util.StringUtil;


/** 使用方法：复制>粘贴>改名>改代码 */
/**带标签的Fragment示例
 * @author Lemon
 * @use new DemoTabFragment(),具体参考.DemoFragmentActivity(initData方法内)
 */
public class DemoTabFragment extends BaseTabFragment implements OnClickListener {
	//	private static final String TAG = "DemoTabFragment";

	//与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	public static final String ARGUMENT_CITY = "ARGUMENT_CITY";

	/**创建一个Fragment实例
	 * @return
	 */
	public static DemoTabFragment createInstance(String city) {
		DemoTabFragment fragment = new DemoTabFragment();

		Bundle bundle = new Bundle();
		bundle.putString(ARGUMENT_CITY, city);

		fragment.setArguments(bundle);
		return fragment;
	}

	//与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	private String city;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState, R.layout.demo_tab_fragment);
		//		needReload = true;

		argument = getArguments();
		if (argument != null) {
			city = argument.getString(ARGUMENT_CITY, city);
		}

		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		return view;
	}



	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private TextView tvDemoTabLeft;
	@Override
	public void initView() {//必须在onCreate方法内调用
		super.initView();

		tvDemoTabLeft = findView(R.id.tvDemoTabLeft);
	}


	/**当需要自定义 tab bar layout时，要实现此方法
	 */
	//	@Override
	//	public int getTopTabViewResId() {
	//		return R.layout.top_tab_view;
	//	}

	/**一行代码没必要新建方法，这里是为了给DemoBottomTabActivity调用
	 */
	public void selectPlace() {
		toActivity(PlacePickerWindow.createIntent(context, context.getPackageName(), 2)
				, REQUEST_TO_PLACE_PICKER, false);
	}
	/**一行代码没必要新建方法，这里是为了给DemoBottomTabActivity调用
	 */
	public void selectMan() {
		toActivity(DemoListActivity.createIntent(context, 0).putExtra(DemoTabActivity.INTENT_TITLE, "筛选"));
	}

	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须在onCreate方法内调用
		super.initData();

		tvDemoTabLeft.setText(StringUtil.isNotEmpty(city, true) ? StringUtil.getTrimedString(city) : "杭州");
	}


	@Override
	protected String[] getTabNames() {
		return new String[] {"附近", "热门"};
	}

	@Override
	protected Fragment getFragment(int position) {
		//示例代码<<<<<<<<<<<<<<<<<<
		return DemoListFragment.createInstance();
		//示例代码>>>>>>>>>>>>>>>>>>
	}



	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须在onCreate方法内调用
		super.initEvent();

		tvDemoTabLeft.setOnClickListener(this);
		findView(R.id.tvDemoTabRight).setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		super.onClick(v);
		int id = v.getId();
		if (id == R.id.tvDemoTabLeft) {
			selectPlace();
		} else if (id == R.id.tvDemoTabRight) {
			selectMan();
		}
	}


	//生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_PLACE_PICKER = 10;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_PLACE_PICKER:
			if (data != null) {
				ArrayList<String> placeList = data.getStringArrayListExtra(PlacePickerWindow.RESULT_PLACE_LIST);
				if (placeList != null && placeList.size() > PlaceUtil.LEVEL_CITY) {
					tvDemoTabLeft.setText(StringUtil.getTrimedString(placeList.get(PlaceUtil.LEVEL_CITY)));
				}
			}
			break;
		default:
			break;
		}
	}

	//生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>







	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}
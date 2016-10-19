package com.example.zhy_pulltorefreash_chenyoca;

import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class PullToRefreshListActivity extends Activity
{

	private LinkedList<String> mListItems;
	/**
	 * 涓婃媺鍒锋柊鐨勬帶浠�
	 */
	private PullToRefreshListView mPullRefreshListView;

	private ArrayAdapter<String> mAdapter;

	private int mItemCount = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 寰楀埌鎺т欢
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		mPullRefreshListView.setMode(Mode.BOTH);
		// 鍒濆鍖栨暟鎹�
		initDatas();
		// 璁剧疆閫傞厤鍣�
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mListItems);
		mPullRefreshListView.setAdapter(mAdapter);

		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener2<ListView>()
				{
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase<ListView> refreshView)
					{
						Log.e("TAG", "onPullDownToRefresh");
						//杩欓噷鍐欎笅鎷夊埛鏂扮殑浠诲姟
						new GetDataTask().execute();
					}

					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase<ListView> refreshView)
					{
						Log.e("TAG", "onPullUpToRefresh");
						//杩欓噷鍐欎笂鎷夊姞杞芥洿澶氱殑浠诲姟
						new GetDataTask().execute();
					}
				});

	}

	private void initDatas()
	{
		// 鍒濆鍖栨暟鎹拰鏁版嵁婧�
		mListItems = new LinkedList<String>();

		for (int i = 0; i < mItemCount; i++)
		{
			mListItems.add("" + i);
		}
	}

	private class GetDataTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{
			try
			{
				Thread.sleep(2000);
			} catch (InterruptedException e)
			{
			}
			return "" + (mItemCount++);
		}

		@Override
		protected void onPostExecute(String result)
		{
			mListItems.add(result);
			mAdapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();
		}
	}

	
}

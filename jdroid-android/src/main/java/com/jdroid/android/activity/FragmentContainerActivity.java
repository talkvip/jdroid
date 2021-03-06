package com.jdroid.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.jdroid.android.R;
import com.jdroid.java.exception.UnexpectedException;

/**
 * 
 * @author Maxi Rosson
 */
public abstract class FragmentContainerActivity extends AbstractFragmentActivity {
	
	/**
	 * @see com.jdroid.android.activity.ActivityIf#getContentView()
	 */
	@Override
	public int getContentView() {
		return R.layout.fragment_container_activity;
	}
	
	/**
	 * @see com.jdroid.android.activity.AbstractFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState == null) {
			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			Fragment fragment = createNewFragment();
			fragmentTransaction.add(R.id.fragmentContainer, fragment);
			if (addToBackStack()) {
				fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
			}
			fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransaction.commit();
		}
	}
	
	protected Boolean addToBackStack() {
		return false;
	}
	
	public void replaceFragment(Fragment newFragment) {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.fragmentContainer, newFragment);
		if (addToBackStack()) {
			fragmentTransaction.addToBackStack(newFragment.getClass().getSimpleName());
		}
		fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		fragmentTransaction.commit();
	}
	
	protected Fragment createNewFragment() {
		return instance(getFragmentClass(), getIntent().getExtras());
	}
	
	private <E extends Fragment> E instance(Class<E> fragmentClass, Bundle bundle) {
		E fragment = null;
		try {
			fragment = fragmentClass.newInstance();
		} catch (InstantiationException e) {
			throw new UnexpectedException(e);
		} catch (IllegalAccessException e) {
			throw new UnexpectedException(e);
		}
		fragment.setArguments(bundle);
		return fragment;
	}
	
	protected Class<? extends Fragment> getFragmentClass() {
		return null;
	}
	
	protected int getTransition() {
		return FragmentTransaction.TRANSIT_FRAGMENT_FADE;
	}
	
	public Fragment getFragment() {
		return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
	}
}

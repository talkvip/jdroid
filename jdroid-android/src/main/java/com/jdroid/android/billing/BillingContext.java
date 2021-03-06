package com.jdroid.android.billing;

import android.util.Log;
import com.jdroid.android.AbstractApplication;
import com.jdroid.java.utils.PropertiesUtils;

/**
 * 
 * @author Maxi Rosson
 */
public class BillingContext implements BillingListener {
	
	private static final String TAG = BillingContext.class.getSimpleName();
	
	private static final String GOOGLE_PLAY_PUBLIC_KEY = PropertiesUtils.getStringProperty("google.play.public.key");
	
	// Whether the in app billing mocks are enabled
	private static final Boolean IN_APP_BILLING_MOCK_ENABLED = PropertiesUtils.getBooleanProperty("inapp.billing.mock.enabled");
	
	private static final BillingContext INSTANCE = new BillingContext();
	
	private BillingManager billingManager;
	private Boolean billingSupported = false;
	
	/**
	 * @return The {@link BillingContext} instance
	 */
	public static BillingContext get() {
		return INSTANCE;
	}
	
	private BillingContext() {
		// Do nothing
	}
	
	public void initialize() {
		billingManager = AbstractApplication.getInstance(BillingManager.class);
		billingManager.register(this);
		
		// Check if billing is supported.
		if (!billingManager.checkBillingSupported()) {
			onGooglePlayConnectionError();
		}
	}
	
	public Class<? extends BillingManager> getBillingManagerClass() {
		return IN_APP_BILLING_MOCK_ENABLED ? MockBillingManager.class : BillingManagerImpl.class;
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onPurchased(com.jdroid.android.billing.PurchaseOrder)
	 */
	@Override
	public void onPurchased(PurchaseOrder order) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onCanceled(com.jdroid.android.billing.PurchaseOrder)
	 */
	@Override
	public void onCanceled(PurchaseOrder order) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onRefunded(com.jdroid.android.billing.PurchaseOrder)
	 */
	@Override
	public void onRefunded(PurchaseOrder order) {
		// Do Nothing
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onBillingSupported()
	 */
	@Override
	public void onBillingSupported() {
		billingSupported = true;
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onBillingNotSupported()
	 */
	@Override
	public void onBillingNotSupported() {
		billingSupported = false;
	}
	
	public Boolean isBillingSupported() {
		return billingSupported;
	}
	
	/**
	 * @see com.jdroid.android.billing.BillingListener#onGooglePlayConnectionError()
	 */
	@Override
	public void onGooglePlayConnectionError() {
		Log.e(TAG, "There was an error connecting with the Google Play app");
	}
	
	public String getGooglePlayPublicKey() {
		return GOOGLE_PLAY_PUBLIC_KEY;
	}
}

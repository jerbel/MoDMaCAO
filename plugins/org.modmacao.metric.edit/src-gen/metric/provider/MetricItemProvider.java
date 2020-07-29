/**
 * Copyright (c) 2015-2017 Obeo, Inria
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 	
 * Contributors:
 * - William Piers <william.piers@obeo.fr>
 * - Philippe Merle <philippe.merle@inria.fr>
 * - Faiez Zalila <faiez.zalila@inria.fr>
 */
package metric.provider;


import java.util.Collection;
import java.util.List;

import metric.Metric;
import metric.MetricPackage;

import org.eclipse.cmf.occi.core.provider.MixinBaseItemProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link metric.Metric} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class MetricItemProvider extends MixinBaseItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetricItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addPredictedMemoryDeployPropertyDescriptor(object);
			addPredictedMemoryUndeployPropertyDescriptor(object);
			addPredictedMemoryStartPropertyDescriptor(object);
			addPredictedMemoryStopPropertyDescriptor(object);
			addPredictedMemoryConfigurePropertyDescriptor(object);
			addActualMemoryDeployPropertyDescriptor(object);
			addActualMemoryUndeployPropertyDescriptor(object);
			addActualMemoryStartPropertyDescriptor(object);
			addActualMemoryStopPropertyDescriptor(object);
			addActualMemoryConfigurePropertyDescriptor(object);
			addPredictedTimeDeployPropertyDescriptor(object);
			addPredictedTimeUndeployPropertyDescriptor(object);
			addPredictedTimeStartPropertyDescriptor(object);
			addPredictedTimeStopPropertyDescriptor(object);
			addPredictedTimeConfigurePropertyDescriptor(object);
			addActualTimeDeployPropertyDescriptor(object);
			addActualTimeUndeployPropertyDescriptor(object);
			addActualTimeStartPropertyDescriptor(object);
			addActualTimeStopPropertyDescriptor(object);
			addActualTimeConfigurePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Predicted Memory Deploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedMemoryDeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedMemoryDeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedMemoryDeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_MEMORY_DEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Memory Undeploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedMemoryUndeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedMemoryUndeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedMemoryUndeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_MEMORY_UNDEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Memory Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedMemoryStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedMemoryStart_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedMemoryStart_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_MEMORY_START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Memory Stop feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedMemoryStopPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedMemoryStop_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedMemoryStop_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_MEMORY_STOP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Memory Configure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedMemoryConfigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedMemoryConfigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedMemoryConfigure_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_MEMORY_CONFIGURE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Memory Deploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualMemoryDeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualMemoryDeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualMemoryDeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_MEMORY_DEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Memory Undeploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualMemoryUndeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualMemoryUndeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualMemoryUndeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_MEMORY_UNDEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Memory Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualMemoryStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualMemoryStart_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualMemoryStart_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_MEMORY_START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Memory Stop feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualMemoryStopPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualMemoryStop_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualMemoryStop_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_MEMORY_STOP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Memory Configure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualMemoryConfigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualMemoryConfigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualMemoryConfigure_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_MEMORY_CONFIGURE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Time Deploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedTimeDeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedTimeDeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedTimeDeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_TIME_DEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Time Undeploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedTimeUndeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedTimeUndeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedTimeUndeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_TIME_UNDEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Time Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedTimeStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedTimeStart_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedTimeStart_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_TIME_START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Time Stop feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedTimeStopPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedTimeStop_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedTimeStop_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_TIME_STOP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Predicted Time Configure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPredictedTimeConfigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_predictedTimeConfigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_predictedTimeConfigure_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__PREDICTED_TIME_CONFIGURE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Time Deploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualTimeDeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualTimeDeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualTimeDeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_TIME_DEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Time Undeploy feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualTimeUndeployPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualTimeUndeploy_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualTimeUndeploy_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_TIME_UNDEPLOY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Time Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualTimeStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualTimeStart_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualTimeStart_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_TIME_START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Time Stop feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualTimeStopPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualTimeStop_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualTimeStop_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_TIME_STOP,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actual Time Configure feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActualTimeConfigurePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Metric_actualTimeConfigure_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Metric_actualTimeConfigure_feature", "_UI_Metric_type"),
				 MetricPackage.Literals.METRIC__ACTUAL_TIME_CONFIGURE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns Metric.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Metric"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Metric)object).getPredictedMemoryDeploy();
		return label == null || label.length() == 0 ?
			getString("_UI_Metric_type") :
			getString("_UI_Metric_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Metric.class)) {
			case MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY:
			case MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY:
			case MetricPackage.METRIC__PREDICTED_MEMORY_START:
			case MetricPackage.METRIC__PREDICTED_MEMORY_STOP:
			case MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE:
			case MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY:
			case MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY:
			case MetricPackage.METRIC__ACTUAL_MEMORY_START:
			case MetricPackage.METRIC__ACTUAL_MEMORY_STOP:
			case MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE:
			case MetricPackage.METRIC__PREDICTED_TIME_DEPLOY:
			case MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY:
			case MetricPackage.METRIC__PREDICTED_TIME_START:
			case MetricPackage.METRIC__PREDICTED_TIME_STOP:
			case MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE:
			case MetricPackage.METRIC__ACTUAL_TIME_DEPLOY:
			case MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY:
			case MetricPackage.METRIC__ACTUAL_TIME_START:
			case MetricPackage.METRIC__ACTUAL_TIME_STOP:
			case MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return MetricEditPlugin.INSTANCE;
	}

}

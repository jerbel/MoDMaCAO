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
package metric.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Map;

import metric.Metric;
import metric.MetricPackage;
import metric.MetricTables;

import org.eclipse.cmf.occi.core.Entity;

import org.eclipse.cmf.occi.core.impl.MixinBaseImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.ocl.pivot.evaluation.Executor;

import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;

import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;

import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;

import org.eclipse.ocl.pivot.utilities.ValueUtil;

import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metric</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link metric.impl.MetricImpl#getPredictedMemoryDeploy <em>Predicted Memory Deploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedMemoryUndeploy <em>Predicted Memory Undeploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedMemoryStart <em>Predicted Memory Start</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedMemoryStop <em>Predicted Memory Stop</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedMemoryConfigure <em>Predicted Memory Configure</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualMemoryDeploy <em>Actual Memory Deploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualMemoryUndeploy <em>Actual Memory Undeploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualMemoryStart <em>Actual Memory Start</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualMemoryStop <em>Actual Memory Stop</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualMemoryConfigure <em>Actual Memory Configure</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedTimeDeploy <em>Predicted Time Deploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedTimeUndeploy <em>Predicted Time Undeploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedTimeStart <em>Predicted Time Start</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedTimeStop <em>Predicted Time Stop</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getPredictedTimeConfigure <em>Predicted Time Configure</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualTimeDeploy <em>Actual Time Deploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualTimeUndeploy <em>Actual Time Undeploy</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualTimeStart <em>Actual Time Start</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualTimeStop <em>Actual Time Stop</em>}</li>
 *   <li>{@link metric.impl.MetricImpl#getActualTimeConfigure <em>Actual Time Configure</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetricImpl extends MixinBaseImpl implements Metric {
	/**
	 * The default value of the '{@link #getPredictedMemoryDeploy() <em>Predicted Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryDeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_MEMORY_DEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedMemoryDeploy() <em>Predicted Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryDeploy()
	 * @generated
	 * @ordered
	 */
	protected String predictedMemoryDeploy = PREDICTED_MEMORY_DEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedMemoryUndeploy() <em>Predicted Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryUndeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_MEMORY_UNDEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedMemoryUndeploy() <em>Predicted Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryUndeploy()
	 * @generated
	 * @ordered
	 */
	protected String predictedMemoryUndeploy = PREDICTED_MEMORY_UNDEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedMemoryStart() <em>Predicted Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryStart()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_MEMORY_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedMemoryStart() <em>Predicted Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryStart()
	 * @generated
	 * @ordered
	 */
	protected String predictedMemoryStart = PREDICTED_MEMORY_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedMemoryStop() <em>Predicted Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryStop()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_MEMORY_STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedMemoryStop() <em>Predicted Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryStop()
	 * @generated
	 * @ordered
	 */
	protected String predictedMemoryStop = PREDICTED_MEMORY_STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedMemoryConfigure() <em>Predicted Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryConfigure()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_MEMORY_CONFIGURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedMemoryConfigure() <em>Predicted Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedMemoryConfigure()
	 * @generated
	 * @ordered
	 */
	protected String predictedMemoryConfigure = PREDICTED_MEMORY_CONFIGURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualMemoryDeploy() <em>Actual Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryDeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_MEMORY_DEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualMemoryDeploy() <em>Actual Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryDeploy()
	 * @generated
	 * @ordered
	 */
	protected String actualMemoryDeploy = ACTUAL_MEMORY_DEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualMemoryUndeploy() <em>Actual Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryUndeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_MEMORY_UNDEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualMemoryUndeploy() <em>Actual Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryUndeploy()
	 * @generated
	 * @ordered
	 */
	protected String actualMemoryUndeploy = ACTUAL_MEMORY_UNDEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualMemoryStart() <em>Actual Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryStart()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_MEMORY_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualMemoryStart() <em>Actual Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryStart()
	 * @generated
	 * @ordered
	 */
	protected String actualMemoryStart = ACTUAL_MEMORY_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualMemoryStop() <em>Actual Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryStop()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_MEMORY_STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualMemoryStop() <em>Actual Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryStop()
	 * @generated
	 * @ordered
	 */
	protected String actualMemoryStop = ACTUAL_MEMORY_STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualMemoryConfigure() <em>Actual Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryConfigure()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_MEMORY_CONFIGURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualMemoryConfigure() <em>Actual Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualMemoryConfigure()
	 * @generated
	 * @ordered
	 */
	protected String actualMemoryConfigure = ACTUAL_MEMORY_CONFIGURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedTimeDeploy() <em>Predicted Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeDeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_TIME_DEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedTimeDeploy() <em>Predicted Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeDeploy()
	 * @generated
	 * @ordered
	 */
	protected String predictedTimeDeploy = PREDICTED_TIME_DEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedTimeUndeploy() <em>Predicted Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeUndeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_TIME_UNDEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedTimeUndeploy() <em>Predicted Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeUndeploy()
	 * @generated
	 * @ordered
	 */
	protected String predictedTimeUndeploy = PREDICTED_TIME_UNDEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedTimeStart() <em>Predicted Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeStart()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_TIME_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedTimeStart() <em>Predicted Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeStart()
	 * @generated
	 * @ordered
	 */
	protected String predictedTimeStart = PREDICTED_TIME_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedTimeStop() <em>Predicted Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeStop()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_TIME_STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedTimeStop() <em>Predicted Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeStop()
	 * @generated
	 * @ordered
	 */
	protected String predictedTimeStop = PREDICTED_TIME_STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getPredictedTimeConfigure() <em>Predicted Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeConfigure()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICTED_TIME_CONFIGURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredictedTimeConfigure() <em>Predicted Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredictedTimeConfigure()
	 * @generated
	 * @ordered
	 */
	protected String predictedTimeConfigure = PREDICTED_TIME_CONFIGURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualTimeDeploy() <em>Actual Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeDeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_TIME_DEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualTimeDeploy() <em>Actual Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeDeploy()
	 * @generated
	 * @ordered
	 */
	protected String actualTimeDeploy = ACTUAL_TIME_DEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualTimeUndeploy() <em>Actual Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeUndeploy()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_TIME_UNDEPLOY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualTimeUndeploy() <em>Actual Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeUndeploy()
	 * @generated
	 * @ordered
	 */
	protected String actualTimeUndeploy = ACTUAL_TIME_UNDEPLOY_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualTimeStart() <em>Actual Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeStart()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_TIME_START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualTimeStart() <em>Actual Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeStart()
	 * @generated
	 * @ordered
	 */
	protected String actualTimeStart = ACTUAL_TIME_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualTimeStop() <em>Actual Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeStop()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_TIME_STOP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualTimeStop() <em>Actual Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeStop()
	 * @generated
	 * @ordered
	 */
	protected String actualTimeStop = ACTUAL_TIME_STOP_EDEFAULT;

	/**
	 * The default value of the '{@link #getActualTimeConfigure() <em>Actual Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeConfigure()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTUAL_TIME_CONFIGURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActualTimeConfigure() <em>Actual Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualTimeConfigure()
	 * @generated
	 * @ordered
	 */
	protected String actualTimeConfigure = ACTUAL_TIME_CONFIGURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetricImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetricPackage.Literals.METRIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedMemoryDeploy() {
		return predictedMemoryDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedMemoryDeploy(String newPredictedMemoryDeploy) {
		String oldPredictedMemoryDeploy = predictedMemoryDeploy;
		predictedMemoryDeploy = newPredictedMemoryDeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY, oldPredictedMemoryDeploy, predictedMemoryDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedMemoryUndeploy() {
		return predictedMemoryUndeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedMemoryUndeploy(String newPredictedMemoryUndeploy) {
		String oldPredictedMemoryUndeploy = predictedMemoryUndeploy;
		predictedMemoryUndeploy = newPredictedMemoryUndeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY, oldPredictedMemoryUndeploy, predictedMemoryUndeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedMemoryStart() {
		return predictedMemoryStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedMemoryStart(String newPredictedMemoryStart) {
		String oldPredictedMemoryStart = predictedMemoryStart;
		predictedMemoryStart = newPredictedMemoryStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_MEMORY_START, oldPredictedMemoryStart, predictedMemoryStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedMemoryStop() {
		return predictedMemoryStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedMemoryStop(String newPredictedMemoryStop) {
		String oldPredictedMemoryStop = predictedMemoryStop;
		predictedMemoryStop = newPredictedMemoryStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_MEMORY_STOP, oldPredictedMemoryStop, predictedMemoryStop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedMemoryConfigure() {
		return predictedMemoryConfigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedMemoryConfigure(String newPredictedMemoryConfigure) {
		String oldPredictedMemoryConfigure = predictedMemoryConfigure;
		predictedMemoryConfigure = newPredictedMemoryConfigure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE, oldPredictedMemoryConfigure, predictedMemoryConfigure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualMemoryDeploy() {
		return actualMemoryDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualMemoryDeploy(String newActualMemoryDeploy) {
		String oldActualMemoryDeploy = actualMemoryDeploy;
		actualMemoryDeploy = newActualMemoryDeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY, oldActualMemoryDeploy, actualMemoryDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualMemoryUndeploy() {
		return actualMemoryUndeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualMemoryUndeploy(String newActualMemoryUndeploy) {
		String oldActualMemoryUndeploy = actualMemoryUndeploy;
		actualMemoryUndeploy = newActualMemoryUndeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY, oldActualMemoryUndeploy, actualMemoryUndeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualMemoryStart() {
		return actualMemoryStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualMemoryStart(String newActualMemoryStart) {
		String oldActualMemoryStart = actualMemoryStart;
		actualMemoryStart = newActualMemoryStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_MEMORY_START, oldActualMemoryStart, actualMemoryStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualMemoryStop() {
		return actualMemoryStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualMemoryStop(String newActualMemoryStop) {
		String oldActualMemoryStop = actualMemoryStop;
		actualMemoryStop = newActualMemoryStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_MEMORY_STOP, oldActualMemoryStop, actualMemoryStop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualMemoryConfigure() {
		return actualMemoryConfigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualMemoryConfigure(String newActualMemoryConfigure) {
		String oldActualMemoryConfigure = actualMemoryConfigure;
		actualMemoryConfigure = newActualMemoryConfigure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE, oldActualMemoryConfigure, actualMemoryConfigure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedTimeDeploy() {
		return predictedTimeDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedTimeDeploy(String newPredictedTimeDeploy) {
		String oldPredictedTimeDeploy = predictedTimeDeploy;
		predictedTimeDeploy = newPredictedTimeDeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_TIME_DEPLOY, oldPredictedTimeDeploy, predictedTimeDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedTimeUndeploy() {
		return predictedTimeUndeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedTimeUndeploy(String newPredictedTimeUndeploy) {
		String oldPredictedTimeUndeploy = predictedTimeUndeploy;
		predictedTimeUndeploy = newPredictedTimeUndeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY, oldPredictedTimeUndeploy, predictedTimeUndeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedTimeStart() {
		return predictedTimeStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedTimeStart(String newPredictedTimeStart) {
		String oldPredictedTimeStart = predictedTimeStart;
		predictedTimeStart = newPredictedTimeStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_TIME_START, oldPredictedTimeStart, predictedTimeStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedTimeStop() {
		return predictedTimeStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedTimeStop(String newPredictedTimeStop) {
		String oldPredictedTimeStop = predictedTimeStop;
		predictedTimeStop = newPredictedTimeStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_TIME_STOP, oldPredictedTimeStop, predictedTimeStop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredictedTimeConfigure() {
		return predictedTimeConfigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredictedTimeConfigure(String newPredictedTimeConfigure) {
		String oldPredictedTimeConfigure = predictedTimeConfigure;
		predictedTimeConfigure = newPredictedTimeConfigure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE, oldPredictedTimeConfigure, predictedTimeConfigure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualTimeDeploy() {
		return actualTimeDeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualTimeDeploy(String newActualTimeDeploy) {
		String oldActualTimeDeploy = actualTimeDeploy;
		actualTimeDeploy = newActualTimeDeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_TIME_DEPLOY, oldActualTimeDeploy, actualTimeDeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualTimeUndeploy() {
		return actualTimeUndeploy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualTimeUndeploy(String newActualTimeUndeploy) {
		String oldActualTimeUndeploy = actualTimeUndeploy;
		actualTimeUndeploy = newActualTimeUndeploy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY, oldActualTimeUndeploy, actualTimeUndeploy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualTimeStart() {
		return actualTimeStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualTimeStart(String newActualTimeStart) {
		String oldActualTimeStart = actualTimeStart;
		actualTimeStart = newActualTimeStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_TIME_START, oldActualTimeStart, actualTimeStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualTimeStop() {
		return actualTimeStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualTimeStop(String newActualTimeStop) {
		String oldActualTimeStop = actualTimeStop;
		actualTimeStop = newActualTimeStop;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_TIME_STOP, oldActualTimeStop, actualTimeStop));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActualTimeConfigure() {
		return actualTimeConfigure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActualTimeConfigure(String newActualTimeConfigure) {
		String oldActualTimeConfigure = actualTimeConfigure;
		actualTimeConfigure = newActualTimeConfigure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE, oldActualTimeConfigure, actualTimeConfigure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean appliesConstraint(final DiagnosticChain diagnostics, final Map<Object, Object> context) {
		/**
		 *
		 * inv appliesConstraint:
		 *   let severity : Integer[1] = 'Metric::appliesConstraint'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : occi::Boolean[1] = self.entity.oclIsKindOf(platform::Application)
		 *       in
		 *         'Metric::appliesConstraint'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, MetricTables.STR_Metric_c_c_appliesConstraint);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, MetricTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ org.eclipse.ocl.pivot.Class TYP_platform_c_c_Application = idResolver.getClass(MetricTables.CLSSid_Application, null);
			final /*@NonInvalid*/ Entity entity = this.getEntity();
			final /*@NonInvalid*/ boolean result = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, entity, TYP_platform_c_c_Application).booleanValue();
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, MetricTables.STR_Metric_c_c_appliesConstraint, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, MetricTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY:
				return getPredictedMemoryDeploy();
			case MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY:
				return getPredictedMemoryUndeploy();
			case MetricPackage.METRIC__PREDICTED_MEMORY_START:
				return getPredictedMemoryStart();
			case MetricPackage.METRIC__PREDICTED_MEMORY_STOP:
				return getPredictedMemoryStop();
			case MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE:
				return getPredictedMemoryConfigure();
			case MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY:
				return getActualMemoryDeploy();
			case MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY:
				return getActualMemoryUndeploy();
			case MetricPackage.METRIC__ACTUAL_MEMORY_START:
				return getActualMemoryStart();
			case MetricPackage.METRIC__ACTUAL_MEMORY_STOP:
				return getActualMemoryStop();
			case MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE:
				return getActualMemoryConfigure();
			case MetricPackage.METRIC__PREDICTED_TIME_DEPLOY:
				return getPredictedTimeDeploy();
			case MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY:
				return getPredictedTimeUndeploy();
			case MetricPackage.METRIC__PREDICTED_TIME_START:
				return getPredictedTimeStart();
			case MetricPackage.METRIC__PREDICTED_TIME_STOP:
				return getPredictedTimeStop();
			case MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE:
				return getPredictedTimeConfigure();
			case MetricPackage.METRIC__ACTUAL_TIME_DEPLOY:
				return getActualTimeDeploy();
			case MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY:
				return getActualTimeUndeploy();
			case MetricPackage.METRIC__ACTUAL_TIME_START:
				return getActualTimeStart();
			case MetricPackage.METRIC__ACTUAL_TIME_STOP:
				return getActualTimeStop();
			case MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE:
				return getActualTimeConfigure();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY:
				setPredictedMemoryDeploy((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY:
				setPredictedMemoryUndeploy((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_START:
				setPredictedMemoryStart((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_STOP:
				setPredictedMemoryStop((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE:
				setPredictedMemoryConfigure((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY:
				setActualMemoryDeploy((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY:
				setActualMemoryUndeploy((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_START:
				setActualMemoryStart((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_STOP:
				setActualMemoryStop((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE:
				setActualMemoryConfigure((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_DEPLOY:
				setPredictedTimeDeploy((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY:
				setPredictedTimeUndeploy((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_START:
				setPredictedTimeStart((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_STOP:
				setPredictedTimeStop((String)newValue);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE:
				setPredictedTimeConfigure((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_DEPLOY:
				setActualTimeDeploy((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY:
				setActualTimeUndeploy((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_START:
				setActualTimeStart((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_STOP:
				setActualTimeStop((String)newValue);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE:
				setActualTimeConfigure((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY:
				setPredictedMemoryDeploy(PREDICTED_MEMORY_DEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY:
				setPredictedMemoryUndeploy(PREDICTED_MEMORY_UNDEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_START:
				setPredictedMemoryStart(PREDICTED_MEMORY_START_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_STOP:
				setPredictedMemoryStop(PREDICTED_MEMORY_STOP_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE:
				setPredictedMemoryConfigure(PREDICTED_MEMORY_CONFIGURE_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY:
				setActualMemoryDeploy(ACTUAL_MEMORY_DEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY:
				setActualMemoryUndeploy(ACTUAL_MEMORY_UNDEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_START:
				setActualMemoryStart(ACTUAL_MEMORY_START_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_STOP:
				setActualMemoryStop(ACTUAL_MEMORY_STOP_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE:
				setActualMemoryConfigure(ACTUAL_MEMORY_CONFIGURE_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_DEPLOY:
				setPredictedTimeDeploy(PREDICTED_TIME_DEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY:
				setPredictedTimeUndeploy(PREDICTED_TIME_UNDEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_START:
				setPredictedTimeStart(PREDICTED_TIME_START_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_STOP:
				setPredictedTimeStop(PREDICTED_TIME_STOP_EDEFAULT);
				return;
			case MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE:
				setPredictedTimeConfigure(PREDICTED_TIME_CONFIGURE_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_DEPLOY:
				setActualTimeDeploy(ACTUAL_TIME_DEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY:
				setActualTimeUndeploy(ACTUAL_TIME_UNDEPLOY_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_START:
				setActualTimeStart(ACTUAL_TIME_START_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_STOP:
				setActualTimeStop(ACTUAL_TIME_STOP_EDEFAULT);
				return;
			case MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE:
				setActualTimeConfigure(ACTUAL_TIME_CONFIGURE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetricPackage.METRIC__PREDICTED_MEMORY_DEPLOY:
				return PREDICTED_MEMORY_DEPLOY_EDEFAULT == null ? predictedMemoryDeploy != null : !PREDICTED_MEMORY_DEPLOY_EDEFAULT.equals(predictedMemoryDeploy);
			case MetricPackage.METRIC__PREDICTED_MEMORY_UNDEPLOY:
				return PREDICTED_MEMORY_UNDEPLOY_EDEFAULT == null ? predictedMemoryUndeploy != null : !PREDICTED_MEMORY_UNDEPLOY_EDEFAULT.equals(predictedMemoryUndeploy);
			case MetricPackage.METRIC__PREDICTED_MEMORY_START:
				return PREDICTED_MEMORY_START_EDEFAULT == null ? predictedMemoryStart != null : !PREDICTED_MEMORY_START_EDEFAULT.equals(predictedMemoryStart);
			case MetricPackage.METRIC__PREDICTED_MEMORY_STOP:
				return PREDICTED_MEMORY_STOP_EDEFAULT == null ? predictedMemoryStop != null : !PREDICTED_MEMORY_STOP_EDEFAULT.equals(predictedMemoryStop);
			case MetricPackage.METRIC__PREDICTED_MEMORY_CONFIGURE:
				return PREDICTED_MEMORY_CONFIGURE_EDEFAULT == null ? predictedMemoryConfigure != null : !PREDICTED_MEMORY_CONFIGURE_EDEFAULT.equals(predictedMemoryConfigure);
			case MetricPackage.METRIC__ACTUAL_MEMORY_DEPLOY:
				return ACTUAL_MEMORY_DEPLOY_EDEFAULT == null ? actualMemoryDeploy != null : !ACTUAL_MEMORY_DEPLOY_EDEFAULT.equals(actualMemoryDeploy);
			case MetricPackage.METRIC__ACTUAL_MEMORY_UNDEPLOY:
				return ACTUAL_MEMORY_UNDEPLOY_EDEFAULT == null ? actualMemoryUndeploy != null : !ACTUAL_MEMORY_UNDEPLOY_EDEFAULT.equals(actualMemoryUndeploy);
			case MetricPackage.METRIC__ACTUAL_MEMORY_START:
				return ACTUAL_MEMORY_START_EDEFAULT == null ? actualMemoryStart != null : !ACTUAL_MEMORY_START_EDEFAULT.equals(actualMemoryStart);
			case MetricPackage.METRIC__ACTUAL_MEMORY_STOP:
				return ACTUAL_MEMORY_STOP_EDEFAULT == null ? actualMemoryStop != null : !ACTUAL_MEMORY_STOP_EDEFAULT.equals(actualMemoryStop);
			case MetricPackage.METRIC__ACTUAL_MEMORY_CONFIGURE:
				return ACTUAL_MEMORY_CONFIGURE_EDEFAULT == null ? actualMemoryConfigure != null : !ACTUAL_MEMORY_CONFIGURE_EDEFAULT.equals(actualMemoryConfigure);
			case MetricPackage.METRIC__PREDICTED_TIME_DEPLOY:
				return PREDICTED_TIME_DEPLOY_EDEFAULT == null ? predictedTimeDeploy != null : !PREDICTED_TIME_DEPLOY_EDEFAULT.equals(predictedTimeDeploy);
			case MetricPackage.METRIC__PREDICTED_TIME_UNDEPLOY:
				return PREDICTED_TIME_UNDEPLOY_EDEFAULT == null ? predictedTimeUndeploy != null : !PREDICTED_TIME_UNDEPLOY_EDEFAULT.equals(predictedTimeUndeploy);
			case MetricPackage.METRIC__PREDICTED_TIME_START:
				return PREDICTED_TIME_START_EDEFAULT == null ? predictedTimeStart != null : !PREDICTED_TIME_START_EDEFAULT.equals(predictedTimeStart);
			case MetricPackage.METRIC__PREDICTED_TIME_STOP:
				return PREDICTED_TIME_STOP_EDEFAULT == null ? predictedTimeStop != null : !PREDICTED_TIME_STOP_EDEFAULT.equals(predictedTimeStop);
			case MetricPackage.METRIC__PREDICTED_TIME_CONFIGURE:
				return PREDICTED_TIME_CONFIGURE_EDEFAULT == null ? predictedTimeConfigure != null : !PREDICTED_TIME_CONFIGURE_EDEFAULT.equals(predictedTimeConfigure);
			case MetricPackage.METRIC__ACTUAL_TIME_DEPLOY:
				return ACTUAL_TIME_DEPLOY_EDEFAULT == null ? actualTimeDeploy != null : !ACTUAL_TIME_DEPLOY_EDEFAULT.equals(actualTimeDeploy);
			case MetricPackage.METRIC__ACTUAL_TIME_UNDEPLOY:
				return ACTUAL_TIME_UNDEPLOY_EDEFAULT == null ? actualTimeUndeploy != null : !ACTUAL_TIME_UNDEPLOY_EDEFAULT.equals(actualTimeUndeploy);
			case MetricPackage.METRIC__ACTUAL_TIME_START:
				return ACTUAL_TIME_START_EDEFAULT == null ? actualTimeStart != null : !ACTUAL_TIME_START_EDEFAULT.equals(actualTimeStart);
			case MetricPackage.METRIC__ACTUAL_TIME_STOP:
				return ACTUAL_TIME_STOP_EDEFAULT == null ? actualTimeStop != null : !ACTUAL_TIME_STOP_EDEFAULT.equals(actualTimeStop);
			case MetricPackage.METRIC__ACTUAL_TIME_CONFIGURE:
				return ACTUAL_TIME_CONFIGURE_EDEFAULT == null ? actualTimeConfigure != null : !ACTUAL_TIME_CONFIGURE_EDEFAULT.equals(actualTimeConfigure);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case MetricPackage.METRIC___APPLIES_CONSTRAINT__DIAGNOSTICCHAIN_MAP:
				return appliesConstraint((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (predictedMemoryDeploy: ");
		result.append(predictedMemoryDeploy);
		result.append(", predictedMemoryUndeploy: ");
		result.append(predictedMemoryUndeploy);
		result.append(", predictedMemoryStart: ");
		result.append(predictedMemoryStart);
		result.append(", predictedMemoryStop: ");
		result.append(predictedMemoryStop);
		result.append(", predictedMemoryConfigure: ");
		result.append(predictedMemoryConfigure);
		result.append(", actualMemoryDeploy: ");
		result.append(actualMemoryDeploy);
		result.append(", actualMemoryUndeploy: ");
		result.append(actualMemoryUndeploy);
		result.append(", actualMemoryStart: ");
		result.append(actualMemoryStart);
		result.append(", actualMemoryStop: ");
		result.append(actualMemoryStop);
		result.append(", actualMemoryConfigure: ");
		result.append(actualMemoryConfigure);
		result.append(", predictedTimeDeploy: ");
		result.append(predictedTimeDeploy);
		result.append(", predictedTimeUndeploy: ");
		result.append(predictedTimeUndeploy);
		result.append(", predictedTimeStart: ");
		result.append(predictedTimeStart);
		result.append(", predictedTimeStop: ");
		result.append(predictedTimeStop);
		result.append(", predictedTimeConfigure: ");
		result.append(predictedTimeConfigure);
		result.append(", actualTimeDeploy: ");
		result.append(actualTimeDeploy);
		result.append(", actualTimeUndeploy: ");
		result.append(actualTimeUndeploy);
		result.append(", actualTimeStart: ");
		result.append(actualTimeStart);
		result.append(", actualTimeStop: ");
		result.append(actualTimeStop);
		result.append(", actualTimeConfigure: ");
		result.append(actualTimeConfigure);
		result.append(')');
		return result.toString();
	}

} //MetricImpl

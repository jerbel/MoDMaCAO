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
package metric;

import org.eclipse.cmf.occi.core.OCCIPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see metric.MetricFactory
 * @model kind="package"
 * @generated
 */
public interface MetricPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "metric";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://schemas.modmacao.org/metric/ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metric";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MetricPackage eINSTANCE = metric.impl.MetricPackageImpl.init();

	/**
	 * The meta object id for the '{@link metric.impl.MetricImpl <em>Metric</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see metric.impl.MetricImpl
	 * @see metric.impl.MetricPackageImpl#getMetric()
	 * @generated
	 */
	int METRIC = 0;

	/**
	 * The feature id for the '<em><b>Mixin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__MIXIN = OCCIPackage.MIXIN_BASE__MIXIN;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ENTITY = OCCIPackage.MIXIN_BASE__ENTITY;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ATTRIBUTES = OCCIPackage.MIXIN_BASE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Predicted Memory Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_MEMORY_DEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Predicted Memory Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_MEMORY_UNDEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Predicted Memory Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_MEMORY_START = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Predicted Memory Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_MEMORY_STOP = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Predicted Memory Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_MEMORY_CONFIGURE = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Actual Memory Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_MEMORY_DEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Actual Memory Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_MEMORY_UNDEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Actual Memory Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_MEMORY_START = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Actual Memory Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_MEMORY_STOP = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Actual Memory Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_MEMORY_CONFIGURE = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Predicted Time Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_TIME_DEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Predicted Time Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_TIME_UNDEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Predicted Time Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_TIME_START = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Predicted Time Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_TIME_STOP = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Predicted Time Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__PREDICTED_TIME_CONFIGURE = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Actual Time Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_TIME_DEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Actual Time Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_TIME_UNDEPLOY = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Actual Time Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_TIME_START = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Actual Time Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_TIME_STOP = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Actual Time Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC__ACTUAL_TIME_CONFIGURE = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 19;

	/**
	 * The number of structural features of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_FEATURE_COUNT = OCCIPackage.MIXIN_BASE_FEATURE_COUNT + 20;

	/**
	 * The operation id for the '<em>Applies Constraint</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC___APPLIES_CONSTRAINT__DIAGNOSTICCHAIN_MAP = OCCIPackage.MIXIN_BASE_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Metric</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METRIC_OPERATION_COUNT = OCCIPackage.MIXIN_BASE_OPERATION_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link metric.Metric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metric</em>'.
	 * @see metric.Metric
	 * @generated
	 */
	EClass getMetric();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedMemoryDeploy <em>Predicted Memory Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Memory Deploy</em>'.
	 * @see metric.Metric#getPredictedMemoryDeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedMemoryDeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedMemoryUndeploy <em>Predicted Memory Undeploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Memory Undeploy</em>'.
	 * @see metric.Metric#getPredictedMemoryUndeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedMemoryUndeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedMemoryStart <em>Predicted Memory Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Memory Start</em>'.
	 * @see metric.Metric#getPredictedMemoryStart()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedMemoryStart();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedMemoryStop <em>Predicted Memory Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Memory Stop</em>'.
	 * @see metric.Metric#getPredictedMemoryStop()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedMemoryStop();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedMemoryConfigure <em>Predicted Memory Configure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Memory Configure</em>'.
	 * @see metric.Metric#getPredictedMemoryConfigure()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedMemoryConfigure();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualMemoryDeploy <em>Actual Memory Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Memory Deploy</em>'.
	 * @see metric.Metric#getActualMemoryDeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualMemoryDeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualMemoryUndeploy <em>Actual Memory Undeploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Memory Undeploy</em>'.
	 * @see metric.Metric#getActualMemoryUndeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualMemoryUndeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualMemoryStart <em>Actual Memory Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Memory Start</em>'.
	 * @see metric.Metric#getActualMemoryStart()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualMemoryStart();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualMemoryStop <em>Actual Memory Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Memory Stop</em>'.
	 * @see metric.Metric#getActualMemoryStop()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualMemoryStop();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualMemoryConfigure <em>Actual Memory Configure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Memory Configure</em>'.
	 * @see metric.Metric#getActualMemoryConfigure()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualMemoryConfigure();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedTimeDeploy <em>Predicted Time Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Time Deploy</em>'.
	 * @see metric.Metric#getPredictedTimeDeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedTimeDeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedTimeUndeploy <em>Predicted Time Undeploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Time Undeploy</em>'.
	 * @see metric.Metric#getPredictedTimeUndeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedTimeUndeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedTimeStart <em>Predicted Time Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Time Start</em>'.
	 * @see metric.Metric#getPredictedTimeStart()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedTimeStart();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedTimeStop <em>Predicted Time Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Time Stop</em>'.
	 * @see metric.Metric#getPredictedTimeStop()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedTimeStop();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getPredictedTimeConfigure <em>Predicted Time Configure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicted Time Configure</em>'.
	 * @see metric.Metric#getPredictedTimeConfigure()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_PredictedTimeConfigure();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualTimeDeploy <em>Actual Time Deploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Time Deploy</em>'.
	 * @see metric.Metric#getActualTimeDeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualTimeDeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualTimeUndeploy <em>Actual Time Undeploy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Time Undeploy</em>'.
	 * @see metric.Metric#getActualTimeUndeploy()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualTimeUndeploy();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualTimeStart <em>Actual Time Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Time Start</em>'.
	 * @see metric.Metric#getActualTimeStart()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualTimeStart();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualTimeStop <em>Actual Time Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Time Stop</em>'.
	 * @see metric.Metric#getActualTimeStop()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualTimeStop();

	/**
	 * Returns the meta object for the attribute '{@link metric.Metric#getActualTimeConfigure <em>Actual Time Configure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actual Time Configure</em>'.
	 * @see metric.Metric#getActualTimeConfigure()
	 * @see #getMetric()
	 * @generated
	 */
	EAttribute getMetric_ActualTimeConfigure();

	/**
	 * Returns the meta object for the '{@link metric.Metric#appliesConstraint(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Applies Constraint</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Applies Constraint</em>' operation.
	 * @see metric.Metric#appliesConstraint(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getMetric__AppliesConstraint__DiagnosticChain_Map();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MetricFactory getMetricFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link metric.impl.MetricImpl <em>Metric</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see metric.impl.MetricImpl
		 * @see metric.impl.MetricPackageImpl#getMetric()
		 * @generated
		 */
		EClass METRIC = eINSTANCE.getMetric();

		/**
		 * The meta object literal for the '<em><b>Predicted Memory Deploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_MEMORY_DEPLOY = eINSTANCE.getMetric_PredictedMemoryDeploy();

		/**
		 * The meta object literal for the '<em><b>Predicted Memory Undeploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_MEMORY_UNDEPLOY = eINSTANCE.getMetric_PredictedMemoryUndeploy();

		/**
		 * The meta object literal for the '<em><b>Predicted Memory Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_MEMORY_START = eINSTANCE.getMetric_PredictedMemoryStart();

		/**
		 * The meta object literal for the '<em><b>Predicted Memory Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_MEMORY_STOP = eINSTANCE.getMetric_PredictedMemoryStop();

		/**
		 * The meta object literal for the '<em><b>Predicted Memory Configure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_MEMORY_CONFIGURE = eINSTANCE.getMetric_PredictedMemoryConfigure();

		/**
		 * The meta object literal for the '<em><b>Actual Memory Deploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_MEMORY_DEPLOY = eINSTANCE.getMetric_ActualMemoryDeploy();

		/**
		 * The meta object literal for the '<em><b>Actual Memory Undeploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_MEMORY_UNDEPLOY = eINSTANCE.getMetric_ActualMemoryUndeploy();

		/**
		 * The meta object literal for the '<em><b>Actual Memory Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_MEMORY_START = eINSTANCE.getMetric_ActualMemoryStart();

		/**
		 * The meta object literal for the '<em><b>Actual Memory Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_MEMORY_STOP = eINSTANCE.getMetric_ActualMemoryStop();

		/**
		 * The meta object literal for the '<em><b>Actual Memory Configure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_MEMORY_CONFIGURE = eINSTANCE.getMetric_ActualMemoryConfigure();

		/**
		 * The meta object literal for the '<em><b>Predicted Time Deploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_TIME_DEPLOY = eINSTANCE.getMetric_PredictedTimeDeploy();

		/**
		 * The meta object literal for the '<em><b>Predicted Time Undeploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_TIME_UNDEPLOY = eINSTANCE.getMetric_PredictedTimeUndeploy();

		/**
		 * The meta object literal for the '<em><b>Predicted Time Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_TIME_START = eINSTANCE.getMetric_PredictedTimeStart();

		/**
		 * The meta object literal for the '<em><b>Predicted Time Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_TIME_STOP = eINSTANCE.getMetric_PredictedTimeStop();

		/**
		 * The meta object literal for the '<em><b>Predicted Time Configure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__PREDICTED_TIME_CONFIGURE = eINSTANCE.getMetric_PredictedTimeConfigure();

		/**
		 * The meta object literal for the '<em><b>Actual Time Deploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_TIME_DEPLOY = eINSTANCE.getMetric_ActualTimeDeploy();

		/**
		 * The meta object literal for the '<em><b>Actual Time Undeploy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_TIME_UNDEPLOY = eINSTANCE.getMetric_ActualTimeUndeploy();

		/**
		 * The meta object literal for the '<em><b>Actual Time Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_TIME_START = eINSTANCE.getMetric_ActualTimeStart();

		/**
		 * The meta object literal for the '<em><b>Actual Time Stop</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_TIME_STOP = eINSTANCE.getMetric_ActualTimeStop();

		/**
		 * The meta object literal for the '<em><b>Actual Time Configure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METRIC__ACTUAL_TIME_CONFIGURE = eINSTANCE.getMetric_ActualTimeConfigure();

		/**
		 * The meta object literal for the '<em><b>Applies Constraint</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation METRIC___APPLIES_CONSTRAINT__DIAGNOSTICCHAIN_MAP = eINSTANCE.getMetric__AppliesConstraint__DiagnosticChain_Map();

	}

} //MetricPackage

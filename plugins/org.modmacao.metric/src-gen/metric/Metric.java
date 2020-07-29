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

import java.util.Map;

import org.eclipse.cmf.occi.core.MixinBase;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Metric</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link metric.Metric#getPredictedMemoryDeploy <em>Predicted Memory Deploy</em>}</li>
 *   <li>{@link metric.Metric#getPredictedMemoryUndeploy <em>Predicted Memory Undeploy</em>}</li>
 *   <li>{@link metric.Metric#getPredictedMemoryStart <em>Predicted Memory Start</em>}</li>
 *   <li>{@link metric.Metric#getPredictedMemoryStop <em>Predicted Memory Stop</em>}</li>
 *   <li>{@link metric.Metric#getPredictedMemoryConfigure <em>Predicted Memory Configure</em>}</li>
 *   <li>{@link metric.Metric#getActualMemoryDeploy <em>Actual Memory Deploy</em>}</li>
 *   <li>{@link metric.Metric#getActualMemoryUndeploy <em>Actual Memory Undeploy</em>}</li>
 *   <li>{@link metric.Metric#getActualMemoryStart <em>Actual Memory Start</em>}</li>
 *   <li>{@link metric.Metric#getActualMemoryStop <em>Actual Memory Stop</em>}</li>
 *   <li>{@link metric.Metric#getActualMemoryConfigure <em>Actual Memory Configure</em>}</li>
 *   <li>{@link metric.Metric#getPredictedTimeDeploy <em>Predicted Time Deploy</em>}</li>
 *   <li>{@link metric.Metric#getPredictedTimeUndeploy <em>Predicted Time Undeploy</em>}</li>
 *   <li>{@link metric.Metric#getPredictedTimeStart <em>Predicted Time Start</em>}</li>
 *   <li>{@link metric.Metric#getPredictedTimeStop <em>Predicted Time Stop</em>}</li>
 *   <li>{@link metric.Metric#getPredictedTimeConfigure <em>Predicted Time Configure</em>}</li>
 *   <li>{@link metric.Metric#getActualTimeDeploy <em>Actual Time Deploy</em>}</li>
 *   <li>{@link metric.Metric#getActualTimeUndeploy <em>Actual Time Undeploy</em>}</li>
 *   <li>{@link metric.Metric#getActualTimeStart <em>Actual Time Start</em>}</li>
 *   <li>{@link metric.Metric#getActualTimeStop <em>Actual Time Stop</em>}</li>
 *   <li>{@link metric.Metric#getActualTimeConfigure <em>Actual Time Configure</em>}</li>
 * </ul>
 *
 * @see metric.MetricPackage#getMetric()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='appliesConstraint'"
 * @generated
 */
public interface Metric extends MixinBase {
	/**
	 * Returns the value of the '<em><b>Predicted Memory Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Memory Deploy</em>' attribute.
	 * @see #setPredictedMemoryDeploy(String)
	 * @see metric.MetricPackage#getMetric_PredictedMemoryDeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedMemoryDeploy'"
	 * @generated
	 */
	String getPredictedMemoryDeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedMemoryDeploy <em>Predicted Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Memory Deploy</em>' attribute.
	 * @see #getPredictedMemoryDeploy()
	 * @generated
	 */
	void setPredictedMemoryDeploy(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Memory Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Memory Undeploy</em>' attribute.
	 * @see #setPredictedMemoryUndeploy(String)
	 * @see metric.MetricPackage#getMetric_PredictedMemoryUndeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedMemoryUndeploy'"
	 * @generated
	 */
	String getPredictedMemoryUndeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedMemoryUndeploy <em>Predicted Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Memory Undeploy</em>' attribute.
	 * @see #getPredictedMemoryUndeploy()
	 * @generated
	 */
	void setPredictedMemoryUndeploy(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Memory Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Memory Start</em>' attribute.
	 * @see #setPredictedMemoryStart(String)
	 * @see metric.MetricPackage#getMetric_PredictedMemoryStart()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedMemoryStart'"
	 * @generated
	 */
	String getPredictedMemoryStart();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedMemoryStart <em>Predicted Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Memory Start</em>' attribute.
	 * @see #getPredictedMemoryStart()
	 * @generated
	 */
	void setPredictedMemoryStart(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Memory Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Memory Stop</em>' attribute.
	 * @see #setPredictedMemoryStop(String)
	 * @see metric.MetricPackage#getMetric_PredictedMemoryStop()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedMemoryStop'"
	 * @generated
	 */
	String getPredictedMemoryStop();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedMemoryStop <em>Predicted Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Memory Stop</em>' attribute.
	 * @see #getPredictedMemoryStop()
	 * @generated
	 */
	void setPredictedMemoryStop(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Memory Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Memory Configure</em>' attribute.
	 * @see #setPredictedMemoryConfigure(String)
	 * @see metric.MetricPackage#getMetric_PredictedMemoryConfigure()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedMemoryConfigure'"
	 * @generated
	 */
	String getPredictedMemoryConfigure();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedMemoryConfigure <em>Predicted Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Memory Configure</em>' attribute.
	 * @see #getPredictedMemoryConfigure()
	 * @generated
	 */
	void setPredictedMemoryConfigure(String value);

	/**
	 * Returns the value of the '<em><b>Actual Memory Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Memory Deploy</em>' attribute.
	 * @see #setActualMemoryDeploy(String)
	 * @see metric.MetricPackage#getMetric_ActualMemoryDeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualMemoryDeploy'"
	 * @generated
	 */
	String getActualMemoryDeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualMemoryDeploy <em>Actual Memory Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Memory Deploy</em>' attribute.
	 * @see #getActualMemoryDeploy()
	 * @generated
	 */
	void setActualMemoryDeploy(String value);

	/**
	 * Returns the value of the '<em><b>Actual Memory Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Memory Undeploy</em>' attribute.
	 * @see #setActualMemoryUndeploy(String)
	 * @see metric.MetricPackage#getMetric_ActualMemoryUndeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualMemoryUndeploy'"
	 * @generated
	 */
	String getActualMemoryUndeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualMemoryUndeploy <em>Actual Memory Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Memory Undeploy</em>' attribute.
	 * @see #getActualMemoryUndeploy()
	 * @generated
	 */
	void setActualMemoryUndeploy(String value);

	/**
	 * Returns the value of the '<em><b>Actual Memory Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Memory Start</em>' attribute.
	 * @see #setActualMemoryStart(String)
	 * @see metric.MetricPackage#getMetric_ActualMemoryStart()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualMemoryStart'"
	 * @generated
	 */
	String getActualMemoryStart();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualMemoryStart <em>Actual Memory Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Memory Start</em>' attribute.
	 * @see #getActualMemoryStart()
	 * @generated
	 */
	void setActualMemoryStart(String value);

	/**
	 * Returns the value of the '<em><b>Actual Memory Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Memory Stop</em>' attribute.
	 * @see #setActualMemoryStop(String)
	 * @see metric.MetricPackage#getMetric_ActualMemoryStop()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualMemoryStop'"
	 * @generated
	 */
	String getActualMemoryStop();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualMemoryStop <em>Actual Memory Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Memory Stop</em>' attribute.
	 * @see #getActualMemoryStop()
	 * @generated
	 */
	void setActualMemoryStop(String value);

	/**
	 * Returns the value of the '<em><b>Actual Memory Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Memory Configure</em>' attribute.
	 * @see #setActualMemoryConfigure(String)
	 * @see metric.MetricPackage#getMetric_ActualMemoryConfigure()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualMemoryConfigure'"
	 * @generated
	 */
	String getActualMemoryConfigure();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualMemoryConfigure <em>Actual Memory Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Memory Configure</em>' attribute.
	 * @see #getActualMemoryConfigure()
	 * @generated
	 */
	void setActualMemoryConfigure(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Time Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Time Deploy</em>' attribute.
	 * @see #setPredictedTimeDeploy(String)
	 * @see metric.MetricPackage#getMetric_PredictedTimeDeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedTimeDeploy'"
	 * @generated
	 */
	String getPredictedTimeDeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedTimeDeploy <em>Predicted Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Time Deploy</em>' attribute.
	 * @see #getPredictedTimeDeploy()
	 * @generated
	 */
	void setPredictedTimeDeploy(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Time Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Time Undeploy</em>' attribute.
	 * @see #setPredictedTimeUndeploy(String)
	 * @see metric.MetricPackage#getMetric_PredictedTimeUndeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedTimeUndeploy'"
	 * @generated
	 */
	String getPredictedTimeUndeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedTimeUndeploy <em>Predicted Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Time Undeploy</em>' attribute.
	 * @see #getPredictedTimeUndeploy()
	 * @generated
	 */
	void setPredictedTimeUndeploy(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Time Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Time Start</em>' attribute.
	 * @see #setPredictedTimeStart(String)
	 * @see metric.MetricPackage#getMetric_PredictedTimeStart()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedTimeStart'"
	 * @generated
	 */
	String getPredictedTimeStart();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedTimeStart <em>Predicted Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Time Start</em>' attribute.
	 * @see #getPredictedTimeStart()
	 * @generated
	 */
	void setPredictedTimeStart(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Time Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Time Stop</em>' attribute.
	 * @see #setPredictedTimeStop(String)
	 * @see metric.MetricPackage#getMetric_PredictedTimeStop()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedTimeStop'"
	 * @generated
	 */
	String getPredictedTimeStop();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedTimeStop <em>Predicted Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Time Stop</em>' attribute.
	 * @see #getPredictedTimeStop()
	 * @generated
	 */
	void setPredictedTimeStop(String value);

	/**
	 * Returns the value of the '<em><b>Predicted Time Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Predicted Time Configure</em>' attribute.
	 * @see #setPredictedTimeConfigure(String)
	 * @see metric.MetricPackage#getMetric_PredictedTimeConfigure()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!predictedTimeConfigure'"
	 * @generated
	 */
	String getPredictedTimeConfigure();

	/**
	 * Sets the value of the '{@link metric.Metric#getPredictedTimeConfigure <em>Predicted Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicted Time Configure</em>' attribute.
	 * @see #getPredictedTimeConfigure()
	 * @generated
	 */
	void setPredictedTimeConfigure(String value);

	/**
	 * Returns the value of the '<em><b>Actual Time Deploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Time Deploy</em>' attribute.
	 * @see #setActualTimeDeploy(String)
	 * @see metric.MetricPackage#getMetric_ActualTimeDeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualTimeDeploy'"
	 * @generated
	 */
	String getActualTimeDeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualTimeDeploy <em>Actual Time Deploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Time Deploy</em>' attribute.
	 * @see #getActualTimeDeploy()
	 * @generated
	 */
	void setActualTimeDeploy(String value);

	/**
	 * Returns the value of the '<em><b>Actual Time Undeploy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Time Undeploy</em>' attribute.
	 * @see #setActualTimeUndeploy(String)
	 * @see metric.MetricPackage#getMetric_ActualTimeUndeploy()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualTimeUndeploy'"
	 * @generated
	 */
	String getActualTimeUndeploy();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualTimeUndeploy <em>Actual Time Undeploy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Time Undeploy</em>' attribute.
	 * @see #getActualTimeUndeploy()
	 * @generated
	 */
	void setActualTimeUndeploy(String value);

	/**
	 * Returns the value of the '<em><b>Actual Time Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Time Start</em>' attribute.
	 * @see #setActualTimeStart(String)
	 * @see metric.MetricPackage#getMetric_ActualTimeStart()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualTimeStart'"
	 * @generated
	 */
	String getActualTimeStart();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualTimeStart <em>Actual Time Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Time Start</em>' attribute.
	 * @see #getActualTimeStart()
	 * @generated
	 */
	void setActualTimeStart(String value);

	/**
	 * Returns the value of the '<em><b>Actual Time Stop</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Time Stop</em>' attribute.
	 * @see #setActualTimeStop(String)
	 * @see metric.MetricPackage#getMetric_ActualTimeStop()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualTimeStop'"
	 * @generated
	 */
	String getActualTimeStop();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualTimeStop <em>Actual Time Stop</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Time Stop</em>' attribute.
	 * @see #getActualTimeStop()
	 * @generated
	 */
	void setActualTimeStop(String value);

	/**
	 * Returns the value of the '<em><b>Actual Time Configure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Time Configure</em>' attribute.
	 * @see #setActualTimeConfigure(String)
	 * @see metric.MetricPackage#getMetric_ActualTimeConfigure()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://schemas.modmacao.org/metric/ecore!Metric!actualTimeConfigure'"
	 * @generated
	 */
	String getActualTimeConfigure();

	/**
	 * Sets the value of the '{@link metric.Metric#getActualTimeConfigure <em>Actual Time Configure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Time Configure</em>' attribute.
	 * @see #getActualTimeConfigure()
	 * @generated
	 */
	void setActualTimeConfigure(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='/**\n *\n * inv appliesConstraint:\n *   let severity : Integer[1] = \'Metric::appliesConstraint\'.getSeverity()\n *   in\n *     if severity &lt;= 0\n *     then true\n *     else\n *       let\n *         result : occi::Boolean[1] = self.entity.oclIsKindOf(platform::Application)\n *       in\n *         \'Metric::appliesConstraint\'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)\n *     endif\n \052/\nfinal /*@NonInvalid\052/ &lt;%org.eclipse.ocl.pivot.evaluation.Executor%&gt; executor = &lt;%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%&gt;.getExecutor(this);\nfinal /*@NonInvalid\052/ &lt;%org.eclipse.ocl.pivot.ids.IdResolver%&gt; idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ &lt;%org.eclipse.ocl.pivot.values.IntegerValue%&gt; severity_0 = &lt;%org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation%&gt;.INSTANCE.evaluate(executor, &lt;%metric.MetricTables%&gt;.STR_Metric_c_c_appliesConstraint);\nfinal /*@NonInvalid\052/ boolean le = &lt;%org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation%&gt;.INSTANCE.evaluate(executor, severity_0, &lt;%metric.MetricTables%&gt;.INT_0).booleanValue();\n/*@NonInvalid\052/ boolean symbol_0;\nif (le) {\n\tsymbol_0 = &lt;%org.eclipse.ocl.pivot.utilities.ValueUtil%&gt;.TRUE_VALUE;\n}\nelse {\n\tfinal /*@NonInvalid\052/ &lt;%org.eclipse.ocl.pivot.Class%&gt; TYP_platform_c_c_Application = idResolver.getClass(&lt;%metric.MetricTables%&gt;.CLSSid_Application, null);\n\tfinal /*@NonInvalid\052/ &lt;%org.eclipse.cmf.occi.core.Entity%&gt; entity = this.getEntity();\n\tfinal /*@NonInvalid\052/ boolean result = &lt;%org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation%&gt;.INSTANCE.evaluate(executor, entity, TYP_platform_c_c_Application).booleanValue();\n\tfinal /*@NonInvalid\052/ boolean logDiagnostic = &lt;%org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation%&gt;.INSTANCE.evaluate(executor, &lt;%org.eclipse.ocl.pivot.ids.TypeId%&gt;.BOOLEAN, &lt;%metric.MetricTables%&gt;.STR_Metric_c_c_appliesConstraint, this, (Object)null, diagnostics, context, (Object)null, severity_0, result, &lt;%metric.MetricTables%&gt;.INT_0).booleanValue();\n\tsymbol_0 = logDiagnostic;\n}\nreturn Boolean.TRUE == symbol_0;'"
	 * @generated
	 */
	boolean appliesConstraint(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Metric

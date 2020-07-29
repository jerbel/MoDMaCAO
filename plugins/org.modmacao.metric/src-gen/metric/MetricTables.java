/*******************************************************************************
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
 *************************************************************************
 * This code is 100% auto-generated
 * from:
 *   /metric/model/metric.ecore
 * using:
 *   /metric/model/metric.genmodel
 *   org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTables
 *
 * Do not edit it.
 *******************************************************************************/
package metric;

import metric.MetricTables;
import org.eclipse.cmf.occi.core.OCCITables;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorProperty;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;

/**
 * MetricTables provides the dispatch tables for the metric for use by the OCL dispatcher.
 *
 * In order to ensure correct static initialization, a top level class element must be accessed
 * before any nested class element. Therefore an access to PACKAGE.getClass() is recommended.
 */
public class MetricTables
{
	static {
		Init.initStart();
	}

	/**
	 *	The package descriptor for the package.
	 */
	public static final /*@NonNull*/ EcoreExecutorPackage PACKAGE = new EcoreExecutorPackage(MetricPackage.eINSTANCE);

	/**
	 *	The library of all packages and types.
	 */
	public static final /*@NonNull*/ ExecutorStandardLibrary LIBRARY = OCLstdlibTables.LIBRARY;

	/**
	 *	Constants used by auto-generated code.
	 */
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.RootPackageId PACKid_$metamodel$ = org.eclipse.ocl.pivot.ids.IdManager.getRootPackageId("$metamodel$");
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.NsURIPackageId PACKid_http_c_s_s_schemas_modmacao_org_s_metric_s_ecore = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://schemas.modmacao.org/metric/ecore", null, metric.MetricPackage.eINSTANCE);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.NsURIPackageId PACKid_http_c_s_s_schemas_modmacao_org_s_occi_s_platform_s_ecore = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://schemas.modmacao.org/occi/platform/ecore", null, org.modmacao.occi.platform.PlatformPackage.eINSTANCE);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.NsURIPackageId PACKid_http_c_s_s_schemas_ogf_org_s_occi_s_core_s_ecore = org.eclipse.ocl.pivot.ids.IdManager.getNsURIPackageId("http://schemas.ogf.org/occi/core/ecore", "occi", org.eclipse.cmf.occi.core.OCCIPackage.eINSTANCE);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.ClassId CLSSid_Application = metric.MetricTables.PACKid_http_c_s_s_schemas_modmacao_org_s_occi_s_platform_s_ecore.getClassId("Application", 0);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.ClassId CLSSid_Class = metric.MetricTables.PACKid_$metamodel$.getClassId("Class", 0);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.ClassId CLSSid_Entity = metric.MetricTables.PACKid_http_c_s_s_schemas_ogf_org_s_occi_s_core_s_ecore.getClassId("Entity", 0);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.ClassId CLSSid_Metric = metric.MetricTables.PACKid_http_c_s_s_schemas_modmacao_org_s_metric_s_ecore.getClassId("Metric", 0);
	public static final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.IntegerValue INT_0 = org.eclipse.ocl.pivot.utilities.ValueUtil.integerValueOf("0");
	public static final /*@NonInvalid*/ java.lang.String STR_Metric_c_c_appliesConstraint = "Metric::appliesConstraint";

	/**
	 *	The type parameters for templated types and operations.
	 */
	public static class TypeParameters {
		static {
			Init.initStart();
			MetricTables.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::TypeParameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The type descriptors for each type.
	 */
	public static class Types {
		static {
			Init.initStart();
			TypeParameters.init();
		}

		public static final /*@NonNull*/ EcoreExecutorType _Metric = new EcoreExecutorType(MetricPackage.Literals.METRIC, PACKAGE, 0);

		private static final /*@NonNull*/ EcoreExecutorType /*@NonNull*/ [] types = {
			_Metric
		};

		/*
		 *	Install the type descriptors in the package descriptor.
		 */
		static {
			PACKAGE.init(LIBRARY, types);
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::Types and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragment descriptors for the local elements of each type and its supertypes.
	 */
	public static class Fragments {
		static {
			Init.initStart();
			Types.init();
		}

		private static final /*@NonNull*/ ExecutorFragment _Metric__Metric = new ExecutorFragment(Types._Metric, MetricTables.Types._Metric);
		private static final /*@NonNull*/ ExecutorFragment _Metric__MixinBase = new ExecutorFragment(Types._Metric, OCCITables.Types._MixinBase);
		private static final /*@NonNull*/ ExecutorFragment _Metric__OclAny = new ExecutorFragment(Types._Metric, OCLstdlibTables.Types._OclAny);
		private static final /*@NonNull*/ ExecutorFragment _Metric__OclElement = new ExecutorFragment(Types._Metric, OCLstdlibTables.Types._OclElement);

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::Fragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The parameter lists shared by operations.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Parameters {
		static {
			Init.initStart();
			Fragments.init();
		}


		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::Parameters and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The operation descriptors for each operation of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Operations {
		static {
			Init.initStart();
			Parameters.init();
		}

		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::Operations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The property descriptors for each property of each type.
	 *
	 * @noextend This class is not intended to be subclassed by clients.
	 * @noinstantiate This class is not intended to be instantiated by clients.
	 * @noreference This class is not intended to be referenced by clients.
	 */
	public static class Properties {
		static {
			Init.initStart();
			Operations.init();
		}

		public static final /*@NonNull*/ ExecutorProperty _Metric__actualMemoryConfigure = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_MEMORY_CONFIGURE, Types._Metric, 0);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualMemoryDeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_MEMORY_DEPLOY, Types._Metric, 1);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualMemoryStart = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_MEMORY_START, Types._Metric, 2);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualMemoryStop = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_MEMORY_STOP, Types._Metric, 3);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualMemoryUndeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_MEMORY_UNDEPLOY, Types._Metric, 4);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualTimeConfigure = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_TIME_CONFIGURE, Types._Metric, 5);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualTimeDeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_TIME_DEPLOY, Types._Metric, 6);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualTimeStart = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_TIME_START, Types._Metric, 7);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualTimeStop = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_TIME_STOP, Types._Metric, 8);
		public static final /*@NonNull*/ ExecutorProperty _Metric__actualTimeUndeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__ACTUAL_TIME_UNDEPLOY, Types._Metric, 9);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedMemoryConfigure = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_MEMORY_CONFIGURE, Types._Metric, 10);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedMemoryDeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_MEMORY_DEPLOY, Types._Metric, 11);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedMemoryStart = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_MEMORY_START, Types._Metric, 12);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedMemoryStop = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_MEMORY_STOP, Types._Metric, 13);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedMemoryUndeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_MEMORY_UNDEPLOY, Types._Metric, 14);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedTimeConfigure = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_TIME_CONFIGURE, Types._Metric, 15);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedTimeDeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_TIME_DEPLOY, Types._Metric, 16);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedTimeStart = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_TIME_START, Types._Metric, 17);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedTimeStop = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_TIME_STOP, Types._Metric, 18);
		public static final /*@NonNull*/ ExecutorProperty _Metric__predictedTimeUndeploy = new EcoreExecutorProperty(MetricPackage.Literals.METRIC__PREDICTED_TIME_UNDEPLOY, Types._Metric, 19);
		static {
			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::Properties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The fragments for all base types in depth order: OclAny first, OclSelf last.
	 */
	public static class TypeFragments {
		static {
			Init.initStart();
			Properties.init();
		}

		private static final /*@NonNull*/ ExecutorFragment /*@NonNull*/ [] _Metric =
			{
				Fragments._Metric__OclAny /* 0 */,
				Fragments._Metric__OclElement /* 1 */,
				Fragments._Metric__MixinBase /* 2 */,
				Fragments._Metric__Metric /* 3 */
			};
		private static final int /*@NonNull*/ [] __Metric = { 1,1,1,1 };

		/**
		 *	Install the fragment descriptors in the class descriptors.
		 */
		static {
			Types._Metric.initFragments(_Metric, __Metric);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::TypeFragments and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local operations or local operation overrides for each fragment of each type.
	 */
	public static class FragmentOperations {
		static {
			Init.initStart();
			TypeFragments.init();
		}

		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Metric__Metric = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Metric__MixinBase = {};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Metric__OclAny = {
			OCLstdlibTables.Operations._OclAny___lt__gt_ /* _'<>'(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny___eq_ /* _'='(OclSelf[?]) */,
			OCLstdlibTables.Operations._OclAny__oclAsSet /* oclAsSet() */,
			OCLstdlibTables.Operations._OclAny__oclAsType /* oclAsType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInState /* oclIsInState(OclState[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsInvalid /* oclIsInvalid() */,
			OCLstdlibTables.Operations._OclAny__oclIsKindOf /* oclIsKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsNew /* oclIsNew() */,
			OCLstdlibTables.Operations._OclAny__oclIsTypeOf /* oclIsTypeOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclAny__oclIsUndefined /* oclIsUndefined() */,
			OCLstdlibTables.Operations._OclAny__0_oclLog /* oclLog() */,
			OCLstdlibTables.Operations._OclAny__1_oclLog /* oclLog(String[?]) */,
			OCLstdlibTables.Operations._OclAny__oclType /* oclType() */,
			OCLstdlibTables.Operations._OclAny__oclTypes /* oclTypes() */,
			OCLstdlibTables.Operations._OclAny__toString /* toString() */
		};
		private static final /*@NonNull*/ ExecutorOperation /*@NonNull*/ [] _Metric__OclElement = {
			OCLstdlibTables.Operations._OclElement__allInstances /* allInstances() */,
			OCLstdlibTables.Operations._OclElement__oclAsModelType /* oclAsModelType(TT)(TT[?]) */,
			OCLstdlibTables.Operations._OclElement__oclContainer /* oclContainer() */,
			OCLstdlibTables.Operations._OclElement__oclContents /* oclContents() */,
			OCLstdlibTables.Operations._OclElement__oclIsModelKindOf /* oclIsModelKindOf(OclType[?]) */,
			OCLstdlibTables.Operations._OclElement__oclModelType /* oclModelType() */,
			OCLstdlibTables.Operations._OclElement__oclModelTypes /* oclModelTypes() */
		};

		/*
		 *	Install the operation descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Metric__Metric.initOperations(_Metric__Metric);
			Fragments._Metric__MixinBase.initOperations(_Metric__MixinBase);
			Fragments._Metric__OclAny.initOperations(_Metric__OclAny);
			Fragments._Metric__OclElement.initOperations(_Metric__OclElement);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::FragmentOperations and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of local properties for the local fragment of each type.
	 */
	public static class FragmentProperties {
		static {
			Init.initStart();
			FragmentOperations.init();
		}

		private static final /*@NonNull*/ ExecutorProperty /*@NonNull*/ [] _Metric = {
			MetricTables.Properties._Metric__actualMemoryConfigure,
			MetricTables.Properties._Metric__actualMemoryDeploy,
			MetricTables.Properties._Metric__actualMemoryStart,
			MetricTables.Properties._Metric__actualMemoryStop,
			MetricTables.Properties._Metric__actualMemoryUndeploy,
			MetricTables.Properties._Metric__actualTimeConfigure,
			MetricTables.Properties._Metric__actualTimeDeploy,
			MetricTables.Properties._Metric__actualTimeStart,
			MetricTables.Properties._Metric__actualTimeStop,
			MetricTables.Properties._Metric__actualTimeUndeploy,
			MetricTables.Properties._Metric__predictedMemoryConfigure,
			MetricTables.Properties._Metric__predictedMemoryDeploy,
			MetricTables.Properties._Metric__predictedMemoryStart,
			MetricTables.Properties._Metric__predictedMemoryStop,
			MetricTables.Properties._Metric__predictedMemoryUndeploy,
			MetricTables.Properties._Metric__predictedTimeConfigure,
			MetricTables.Properties._Metric__predictedTimeDeploy,
			MetricTables.Properties._Metric__predictedTimeStart,
			MetricTables.Properties._Metric__predictedTimeStop,
			MetricTables.Properties._Metric__predictedTimeUndeploy
		};

		/**
		 *	Install the property descriptors in the fragment descriptors.
		 */
		static {
			Fragments._Metric__Metric.initProperties(_Metric);

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::FragmentProperties and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 *	The lists of enumeration literals for each enumeration.
	 */
	public static class EnumerationLiterals {
		static {
			Init.initStart();
			FragmentProperties.init();
		}

		/**
		 *	Install the enumeration literals in the enumerations.
		 */
		static {

			Init.initEnd();
		}

		/**
		 * Force initialization of the fields of MetricTables::EnumerationLiterals and all preceding sub-packages.
		 */
		public static void init() {}
	}

	/**
	 * The multiple packages above avoid problems with the Java 65536 byte limit but introduce a difficulty in ensuring that
	 * static construction occurs in the disciplined order of the packages when construction may start in any of the packages.
	 * The problem is resolved by ensuring that the static construction of each package first initializes its immediate predecessor.
	 * On completion of predecessor initialization, the residual packages are initialized by starting an initialization in the last package.
	 * This class maintains a count so that the various predecessors can distinguish whether they are the starting point and so
	 * ensure that residual construction occurs just once after all predecessors.
	 */
	private static class Init {
		/**
		 * Counter of nested static constructions. On return to zero residual construction starts. -ve once residual construction started.
		 */
		private static int initCount = 0;

		/**
		 * Invoked at the start of a static construction to defer residual cobstruction until primary constructions complete.
		 */
		private static void initStart() {
			if (initCount >= 0) {
				initCount++;
			}
		}

		/**
		 * Invoked at the end of a static construction to activate residual cobstruction once primary constructions complete.
		 */
		private static void initEnd() {
			if (initCount > 0) {
				if (--initCount == 0) {
					initCount = -1;
					EnumerationLiterals.init();
				}
			}
		}
	}

	static {
		Init.initEnd();
	}

	/*
	 * Force initialization of outer fields. Inner fields are lazily initialized.
	 */
	public static void init() {}
}

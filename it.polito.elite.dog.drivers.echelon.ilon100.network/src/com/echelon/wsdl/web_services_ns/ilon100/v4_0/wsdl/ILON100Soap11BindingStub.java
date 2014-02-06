/**
 * ILON100Soap11BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl;
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class ILON100Soap11BindingStub extends org.apache.axis.client.Stub implements com.echelon.wsdl.web_services_ns.ilon100.v4_0.wsdl.ILON100PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("List");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_xSelect"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Get");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_CfgColl"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Set");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_CfgColl"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Delete");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Read");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_DataColl"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Write");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_DataColl"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Clear");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("InvokeCmd");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"), com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll"));
        oper.setReturnClass(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "iLonItem"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public ILON100Soap11BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ILON100Soap11BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ILON100Soap11BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
        addBindings0();
        addBindings1();
    }


    private void addBindings0() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTcalendar_Cfg>Exception>Client");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionClient.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTcalendar_Cfg>Exception>Schedule");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgExceptionSchedule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTscheduler_Cfg>DateBased>Exception");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBasedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTscheduler_Cfg>DayBased>Weekdays");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBasedWeekdays.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">>UFPTtypeTranslator_Rule_Cfg>Case>Rule");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCaseRule.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">Dp_Ref>DataPoint");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_RefDataPoint.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">E_Fault>faultcode");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_FaultFaultcode.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">E_Variant>Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_VariantData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>Address");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>Command");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>UCPTurlCpFile");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_ProxyData_InvokeResponse>ProxyAgent");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_ProxyData_InvokeResponseProxyAgent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_RepeatingData_InvokeResponse>RepeatChain");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponseRepeatChain.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_Cfg>Domain");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgDomain.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_Cfg>LnsDatabase");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_CfgLnsDatabase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Network_ScanCommand_Invoke>Command");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_InvokeCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MBS_Device_Cfg>Address");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_CfgAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">MOD_Device_Cfg>Address");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_CfgAddress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgBitfield>Scale");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfieldScale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgScalar>Scale");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalarScale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgStruct>Member");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_ENUM_Cfg>Member");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_CfgMember.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_FPT_Cfg>CP");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgCP.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_FPT_Cfg>NV");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_CfgNV.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>AlarmDest");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Cfg>Mail");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgMail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Data>ReadData");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DataReadData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTalarmNotifier_Input_DpRef>AlarmFlags");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRefAlarmFlags.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTcalendar_Cfg>Exception");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTscheduler_Cfg>DateBased");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDateBased.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTscheduler_Cfg>DayBased");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgDayBased.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>Case");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgCase.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">UFPTtypeTranslator_Rule_Cfg>DataPointFormat");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_CfgDataPointFormat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Channel_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Channel_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Device_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Device_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Cfg_Base");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Cfg_Base.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_CfgValueDef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_CfgValueDef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_eType");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_eType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_Ref");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_Ref.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Dp_ResetPrio_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Dp_ResetPrio_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "DpRef_eDiscriminator");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.DpRef_eDiscriminator.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Dummy");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Dummy.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Fault");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Flag");
            cachedSerQNames.add(qName);
            cls = short.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_InterfaceOptions");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_InterfaceOptions.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Path");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Path.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Priority");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Unit");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Unit.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_Variant");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Variant.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_xSelect");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Fault_eType");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fault_eType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Fb_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Fb_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Fb_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FileSystem_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.FileSystem_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FileSystem_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.FileSystem_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "FPM_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.FPM_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_CfgColl");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Coll");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_DataColl");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Item_Service");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Service.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Channel_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Channel_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Cp_Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Cp_Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Cp_File_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Cp_File_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_Command_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_Command_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_DataFrequencyInfo");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_DataFrequencyInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_eCommand");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_eCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_IlonNi_eCommand");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_IlonNi_eCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_NetworkInterface_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_NetworkInterface_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_PowerlineData_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_PowerlineData_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_ProxyData_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_ProxyData_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_RepeatingData_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RepeatingData_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_RNI_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_RNI_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_Router_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_Router_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_StatusData_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_StatusData_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Fb_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Fb_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_Command_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_Command_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_DeviceCommand_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_DeviceCommand_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_eCommand");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_eScanCommand");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_eScanCommand.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Network_ScanCommand_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Network_ScanCommand_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Channel_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Channel_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Device_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Device_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Fb_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Fb_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MBS_Network_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MBS_Network_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Channel_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Channel_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Device_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Device_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Fb_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Fb_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "MOD_Network_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.MOD_Network_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Network_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Network_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgBitfield");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgBitfield.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgEnum");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgEnum.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFloat");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFloat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgFormatString");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgFormatString.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgScalar");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgScalar.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CfgStruct");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CfgStructMember[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">TemplateManager_CfgStruct>Member");
            qName2 = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Member");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_CPT_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_CPT_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_Data_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Data_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_Data_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Data_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_ENUM_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_ENUM_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_FILE_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FILE_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_FPT_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_FPT_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_NVT_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_NVT_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "TemplateManager_Surrogate_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.TemplateManager_Surrogate_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmGenerator_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmGenerator_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarm");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarm.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_CfgAlarmDestination");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_CfgAlarmDestination.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_ClearResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_ClearResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Input_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Input_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTalarmNotifier_Meta_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTalarmNotifier_Meta_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTanalogFunctionBlock_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTanalogFunctionBlock_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTanalogFunctionBlock_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTanalogFunctionBlock_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_CfgESDate");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_CfgESDate.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTcalendar_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTcalendar_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdataLogger_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_ClearResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdataLogger_ClearResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdataLogger_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdataLogger_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdataLogger_Meta_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdataLogger_Meta_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdigitalInput_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdigitalInput_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTdigitalOutput_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTdigitalOutput_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTnodeObject_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTnodeObject_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTpulseCounter_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTpulseCounter_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTrealTimeClock_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTrealTimeClock_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTrealTimeClock_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTrealTimeClock_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTrealTimeClock_Data_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTrealTimeClock_Data_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_BaseTime_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_BaseTime_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Calendar_Meta_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_Calendar_Meta_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Calendar_Request_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_Calendar_Request_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEffectivePeriod");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEffectivePeriod.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_CfgEvent");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_CfgEvent.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_ListSchedules_Invoke");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_ListSchedules_Invoke.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_ListSchedules_InvokeResponse");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_ListSchedules_InvokeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_Meta_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_Meta_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_RealTimeClock_Meta_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_RealTimeClock_Meta_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTscheduler_RealTimeClock_Request_Data");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTscheduler_RealTimeClock_Request_Data.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTtypeTranslator_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTtypeTranslator_DpRef");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_DpRef.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UFPTtypeTranslator_Rule_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.UFPTtypeTranslator_Rule_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Channel_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Virtual_Channel_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Device_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Virtual_Device_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Dp_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Virtual_Dp_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Fb_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Virtual_Fb_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Virtual_Network_Cfg");
            cachedSerQNames.add(qName);
            cls = com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Virtual_Network_Cfg.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll list(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_xSelect iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/List");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "List"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl get(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Get");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Get"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll set(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_CfgColl iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Set");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Set"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll delete(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Delete");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Delete"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl read(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Read");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Read"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll write(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_DataColl iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Write");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Write"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll clear(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/Clear");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Clear"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll invokeCmd(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll iLonItem) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/action/InvokeCmd");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "InvokeCmd"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iLonItem});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll) org.apache.axis.utils.JavaUtils.convert(_resp, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Coll.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}

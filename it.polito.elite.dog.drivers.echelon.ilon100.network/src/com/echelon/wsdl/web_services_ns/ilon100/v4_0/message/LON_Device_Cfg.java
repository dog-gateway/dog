/**
 * LON_Device_Cfg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.echelon.wsdl.web_services_ns.ilon100.v4_0.message;


/**
 * ATTENTION: use the 'LON_Device_Command_Invoke' for LON_Device_eCommand:
 * 				'QueryStatus', 'ClearStatus', 'RepeatingData', 'PowerlineData',
 * 'ProxyData'
 */
@SuppressWarnings({"rawtypes","unused"})
public class LON_Device_Cfg  extends com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.Item_Cfg  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle;

    private byte[] UCPTuniqueId;

    private byte[] UCPTreplacementId;

    private byte[] UCPTprogramId;

    private java.lang.String UCPTgeoPosition;

    private byte[] UCPTlocationId;

    private java.lang.Integer UCPTmaxDynamicFb;

    private java.lang.Integer UCPTmaxDynamicDp;

    private java.lang.Integer UCPTmaxTxTransactions;

    private java.lang.Integer UCPTmaxTxLifetime;

    private java.lang.Short UCPTlocal;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTapplicationStatus;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcommissionStatus;

    private java.lang.String UCPTurlImage;

    private java.lang.String UCPTurlTemplate;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile[] UCPTurlCpFile;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress[] address;

    private com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand[] command;

    public LON_Device_Cfg() {
    }

    public LON_Device_Cfg(
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_Fault fault,
           java.lang.String UCPTname,
           java.lang.String UCPTannotation,
           java.lang.Short UCPThidden,
           java.lang.String UCPTaliasName,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTitemStatus,
           java.util.Calendar UCPTlastUpdate,
           java.lang.String UCPTdescription,
           java.lang.String UCPTuri,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle,
           byte[] UCPTuniqueId,
           byte[] UCPTreplacementId,
           byte[] UCPTprogramId,
           java.lang.String UCPTgeoPosition,
           byte[] UCPTlocationId,
           java.lang.Integer UCPTmaxDynamicFb,
           java.lang.Integer UCPTmaxDynamicDp,
           java.lang.Integer UCPTmaxTxTransactions,
           java.lang.Integer UCPTmaxTxLifetime,
           java.lang.Short UCPTlocal,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTapplicationStatus,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcommissionStatus,
           java.lang.String UCPTurlImage,
           java.lang.String UCPTurlTemplate,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile[] UCPTurlCpFile,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress[] address,
           com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand[] command) {
        super(
            fault,
            UCPTname,
            UCPTannotation,
            UCPThidden,
            UCPTaliasName,
            UCPTitemStatus,
            UCPTlastUpdate,
            UCPTdescription,
            UCPTuri);
        this.UCPThandle = UCPThandle;
        this.UCPTuniqueId = UCPTuniqueId;
        this.UCPTreplacementId = UCPTreplacementId;
        this.UCPTprogramId = UCPTprogramId;
        this.UCPTgeoPosition = UCPTgeoPosition;
        this.UCPTlocationId = UCPTlocationId;
        this.UCPTmaxDynamicFb = UCPTmaxDynamicFb;
        this.UCPTmaxDynamicDp = UCPTmaxDynamicDp;
        this.UCPTmaxTxTransactions = UCPTmaxTxTransactions;
        this.UCPTmaxTxLifetime = UCPTmaxTxLifetime;
        this.UCPTlocal = UCPTlocal;
        this.UCPTapplicationStatus = UCPTapplicationStatus;
        this.UCPTcommissionStatus = UCPTcommissionStatus;
        this.UCPTurlImage = UCPTurlImage;
        this.UCPTurlTemplate = UCPTurlTemplate;
        this.UCPTdynamic = UCPTdynamic;
        this.UCPTurlCpFile = UCPTurlCpFile;
        this.address = address;
        this.command = command;
    }


    /**
     * Gets the UCPThandle value for this LON_Device_Cfg.
     * 
     * @return UCPThandle
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey getUCPThandle() {
        return UCPThandle;
    }


    /**
     * Sets the UCPThandle value for this LON_Device_Cfg.
     * 
     * @param UCPThandle
     */
    public void setUCPThandle(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_UniqueKey UCPThandle) {
        this.UCPThandle = UCPThandle;
    }


    /**
     * Gets the UCPTuniqueId value for this LON_Device_Cfg.
     * 
     * @return UCPTuniqueId
     */
    public byte[] getUCPTuniqueId() {
        return UCPTuniqueId;
    }


    /**
     * Sets the UCPTuniqueId value for this LON_Device_Cfg.
     * 
     * @param UCPTuniqueId
     */
    public void setUCPTuniqueId(byte[] UCPTuniqueId) {
        this.UCPTuniqueId = UCPTuniqueId;
    }


    /**
     * Gets the UCPTreplacementId value for this LON_Device_Cfg.
     * 
     * @return UCPTreplacementId
     */
    public byte[] getUCPTreplacementId() {
        return UCPTreplacementId;
    }


    /**
     * Sets the UCPTreplacementId value for this LON_Device_Cfg.
     * 
     * @param UCPTreplacementId
     */
    public void setUCPTreplacementId(byte[] UCPTreplacementId) {
        this.UCPTreplacementId = UCPTreplacementId;
    }


    /**
     * Gets the UCPTprogramId value for this LON_Device_Cfg.
     * 
     * @return UCPTprogramId
     */
    public byte[] getUCPTprogramId() {
        return UCPTprogramId;
    }


    /**
     * Sets the UCPTprogramId value for this LON_Device_Cfg.
     * 
     * @param UCPTprogramId
     */
    public void setUCPTprogramId(byte[] UCPTprogramId) {
        this.UCPTprogramId = UCPTprogramId;
    }


    /**
     * Gets the UCPTgeoPosition value for this LON_Device_Cfg.
     * 
     * @return UCPTgeoPosition
     */
    public java.lang.String getUCPTgeoPosition() {
        return UCPTgeoPosition;
    }


    /**
     * Sets the UCPTgeoPosition value for this LON_Device_Cfg.
     * 
     * @param UCPTgeoPosition
     */
    public void setUCPTgeoPosition(java.lang.String UCPTgeoPosition) {
        this.UCPTgeoPosition = UCPTgeoPosition;
    }


    /**
     * Gets the UCPTlocationId value for this LON_Device_Cfg.
     * 
     * @return UCPTlocationId
     */
    public byte[] getUCPTlocationId() {
        return UCPTlocationId;
    }


    /**
     * Sets the UCPTlocationId value for this LON_Device_Cfg.
     * 
     * @param UCPTlocationId
     */
    public void setUCPTlocationId(byte[] UCPTlocationId) {
        this.UCPTlocationId = UCPTlocationId;
    }


    /**
     * Gets the UCPTmaxDynamicFb value for this LON_Device_Cfg.
     * 
     * @return UCPTmaxDynamicFb
     */
    public java.lang.Integer getUCPTmaxDynamicFb() {
        return UCPTmaxDynamicFb;
    }


    /**
     * Sets the UCPTmaxDynamicFb value for this LON_Device_Cfg.
     * 
     * @param UCPTmaxDynamicFb
     */
    public void setUCPTmaxDynamicFb(java.lang.Integer UCPTmaxDynamicFb) {
        this.UCPTmaxDynamicFb = UCPTmaxDynamicFb;
    }


    /**
     * Gets the UCPTmaxDynamicDp value for this LON_Device_Cfg.
     * 
     * @return UCPTmaxDynamicDp
     */
    public java.lang.Integer getUCPTmaxDynamicDp() {
        return UCPTmaxDynamicDp;
    }


    /**
     * Sets the UCPTmaxDynamicDp value for this LON_Device_Cfg.
     * 
     * @param UCPTmaxDynamicDp
     */
    public void setUCPTmaxDynamicDp(java.lang.Integer UCPTmaxDynamicDp) {
        this.UCPTmaxDynamicDp = UCPTmaxDynamicDp;
    }


    /**
     * Gets the UCPTmaxTxTransactions value for this LON_Device_Cfg.
     * 
     * @return UCPTmaxTxTransactions
     */
    public java.lang.Integer getUCPTmaxTxTransactions() {
        return UCPTmaxTxTransactions;
    }


    /**
     * Sets the UCPTmaxTxTransactions value for this LON_Device_Cfg.
     * 
     * @param UCPTmaxTxTransactions
     */
    public void setUCPTmaxTxTransactions(java.lang.Integer UCPTmaxTxTransactions) {
        this.UCPTmaxTxTransactions = UCPTmaxTxTransactions;
    }


    /**
     * Gets the UCPTmaxTxLifetime value for this LON_Device_Cfg.
     * 
     * @return UCPTmaxTxLifetime
     */
    public java.lang.Integer getUCPTmaxTxLifetime() {
        return UCPTmaxTxLifetime;
    }


    /**
     * Sets the UCPTmaxTxLifetime value for this LON_Device_Cfg.
     * 
     * @param UCPTmaxTxLifetime
     */
    public void setUCPTmaxTxLifetime(java.lang.Integer UCPTmaxTxLifetime) {
        this.UCPTmaxTxLifetime = UCPTmaxTxLifetime;
    }


    /**
     * Gets the UCPTlocal value for this LON_Device_Cfg.
     * 
     * @return UCPTlocal
     */
    public java.lang.Short getUCPTlocal() {
        return UCPTlocal;
    }


    /**
     * Sets the UCPTlocal value for this LON_Device_Cfg.
     * 
     * @param UCPTlocal
     */
    public void setUCPTlocal(java.lang.Short UCPTlocal) {
        this.UCPTlocal = UCPTlocal;
    }


    /**
     * Gets the UCPTapplicationStatus value for this LON_Device_Cfg.
     * 
     * @return UCPTapplicationStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTapplicationStatus() {
        return UCPTapplicationStatus;
    }


    /**
     * Sets the UCPTapplicationStatus value for this LON_Device_Cfg.
     * 
     * @param UCPTapplicationStatus
     */
    public void setUCPTapplicationStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTapplicationStatus) {
        this.UCPTapplicationStatus = UCPTapplicationStatus;
    }


    /**
     * Gets the UCPTcommissionStatus value for this LON_Device_Cfg.
     * 
     * @return UCPTcommissionStatus
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTcommissionStatus() {
        return UCPTcommissionStatus;
    }


    /**
     * Sets the UCPTcommissionStatus value for this LON_Device_Cfg.
     * 
     * @param UCPTcommissionStatus
     */
    public void setUCPTcommissionStatus(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTcommissionStatus) {
        this.UCPTcommissionStatus = UCPTcommissionStatus;
    }


    /**
     * Gets the UCPTurlImage value for this LON_Device_Cfg.
     * 
     * @return UCPTurlImage
     */
    public java.lang.String getUCPTurlImage() {
        return UCPTurlImage;
    }


    /**
     * Sets the UCPTurlImage value for this LON_Device_Cfg.
     * 
     * @param UCPTurlImage
     */
    public void setUCPTurlImage(java.lang.String UCPTurlImage) {
        this.UCPTurlImage = UCPTurlImage;
    }


    /**
     * Gets the UCPTurlTemplate value for this LON_Device_Cfg.
     * 
     * @return UCPTurlTemplate
     */
    public java.lang.String getUCPTurlTemplate() {
        return UCPTurlTemplate;
    }


    /**
     * Sets the UCPTurlTemplate value for this LON_Device_Cfg.
     * 
     * @param UCPTurlTemplate
     */
    public void setUCPTurlTemplate(java.lang.String UCPTurlTemplate) {
        this.UCPTurlTemplate = UCPTurlTemplate;
    }


    /**
     * Gets the UCPTdynamic value for this LON_Device_Cfg.
     * 
     * @return UCPTdynamic
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString getUCPTdynamic() {
        return UCPTdynamic;
    }


    /**
     * Sets the UCPTdynamic value for this LON_Device_Cfg.
     * 
     * @param UCPTdynamic
     */
    public void setUCPTdynamic(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.E_LonString UCPTdynamic) {
        this.UCPTdynamic = UCPTdynamic;
    }


    /**
     * Gets the UCPTurlCpFile value for this LON_Device_Cfg.
     * 
     * @return UCPTurlCpFile
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile[] getUCPTurlCpFile() {
        return UCPTurlCpFile;
    }


    /**
     * Sets the UCPTurlCpFile value for this LON_Device_Cfg.
     * 
     * @param UCPTurlCpFile
     */
    public void setUCPTurlCpFile(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile[] UCPTurlCpFile) {
        this.UCPTurlCpFile = UCPTurlCpFile;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile getUCPTurlCpFile(int i) {
        return this.UCPTurlCpFile[i];
    }

    public void setUCPTurlCpFile(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgUCPTurlCpFile _value) {
        this.UCPTurlCpFile[i] = _value;
    }


    /**
     * Gets the address value for this LON_Device_Cfg.
     * 
     * @return address
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress[] getAddress() {
        return address;
    }


    /**
     * Sets the address value for this LON_Device_Cfg.
     * 
     * @param address
     */
    public void setAddress(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress[] address) {
        this.address = address;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress getAddress(int i) {
        return this.address[i];
    }

    public void setAddress(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgAddress _value) {
        this.address[i] = _value;
    }


    /**
     * Gets the command value for this LON_Device_Cfg.
     * 
     * @return command
     */
    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand[] getCommand() {
        return command;
    }


    /**
     * Sets the command value for this LON_Device_Cfg.
     * 
     * @param command
     */
    public void setCommand(com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand[] command) {
        this.command = command;
    }

    public com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand getCommand(int i) {
        return this.command[i];
    }

    public void setCommand(int i, com.echelon.wsdl.web_services_ns.ilon100.v4_0.message.LON_Device_CfgCommand _value) {
        this.command[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LON_Device_Cfg)) return false;
        LON_Device_Cfg other = (LON_Device_Cfg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.UCPThandle==null && other.getUCPThandle()==null) || 
             (this.UCPThandle!=null &&
              this.UCPThandle.equals(other.getUCPThandle()))) &&
            ((this.UCPTuniqueId==null && other.getUCPTuniqueId()==null) || 
             (this.UCPTuniqueId!=null &&
              java.util.Arrays.equals(this.UCPTuniqueId, other.getUCPTuniqueId()))) &&
            ((this.UCPTreplacementId==null && other.getUCPTreplacementId()==null) || 
             (this.UCPTreplacementId!=null &&
              java.util.Arrays.equals(this.UCPTreplacementId, other.getUCPTreplacementId()))) &&
            ((this.UCPTprogramId==null && other.getUCPTprogramId()==null) || 
             (this.UCPTprogramId!=null &&
              java.util.Arrays.equals(this.UCPTprogramId, other.getUCPTprogramId()))) &&
            ((this.UCPTgeoPosition==null && other.getUCPTgeoPosition()==null) || 
             (this.UCPTgeoPosition!=null &&
              this.UCPTgeoPosition.equals(other.getUCPTgeoPosition()))) &&
            ((this.UCPTlocationId==null && other.getUCPTlocationId()==null) || 
             (this.UCPTlocationId!=null &&
              java.util.Arrays.equals(this.UCPTlocationId, other.getUCPTlocationId()))) &&
            ((this.UCPTmaxDynamicFb==null && other.getUCPTmaxDynamicFb()==null) || 
             (this.UCPTmaxDynamicFb!=null &&
              this.UCPTmaxDynamicFb.equals(other.getUCPTmaxDynamicFb()))) &&
            ((this.UCPTmaxDynamicDp==null && other.getUCPTmaxDynamicDp()==null) || 
             (this.UCPTmaxDynamicDp!=null &&
              this.UCPTmaxDynamicDp.equals(other.getUCPTmaxDynamicDp()))) &&
            ((this.UCPTmaxTxTransactions==null && other.getUCPTmaxTxTransactions()==null) || 
             (this.UCPTmaxTxTransactions!=null &&
              this.UCPTmaxTxTransactions.equals(other.getUCPTmaxTxTransactions()))) &&
            ((this.UCPTmaxTxLifetime==null && other.getUCPTmaxTxLifetime()==null) || 
             (this.UCPTmaxTxLifetime!=null &&
              this.UCPTmaxTxLifetime.equals(other.getUCPTmaxTxLifetime()))) &&
            ((this.UCPTlocal==null && other.getUCPTlocal()==null) || 
             (this.UCPTlocal!=null &&
              this.UCPTlocal.equals(other.getUCPTlocal()))) &&
            ((this.UCPTapplicationStatus==null && other.getUCPTapplicationStatus()==null) || 
             (this.UCPTapplicationStatus!=null &&
              this.UCPTapplicationStatus.equals(other.getUCPTapplicationStatus()))) &&
            ((this.UCPTcommissionStatus==null && other.getUCPTcommissionStatus()==null) || 
             (this.UCPTcommissionStatus!=null &&
              this.UCPTcommissionStatus.equals(other.getUCPTcommissionStatus()))) &&
            ((this.UCPTurlImage==null && other.getUCPTurlImage()==null) || 
             (this.UCPTurlImage!=null &&
              this.UCPTurlImage.equals(other.getUCPTurlImage()))) &&
            ((this.UCPTurlTemplate==null && other.getUCPTurlTemplate()==null) || 
             (this.UCPTurlTemplate!=null &&
              this.UCPTurlTemplate.equals(other.getUCPTurlTemplate()))) &&
            ((this.UCPTdynamic==null && other.getUCPTdynamic()==null) || 
             (this.UCPTdynamic!=null &&
              this.UCPTdynamic.equals(other.getUCPTdynamic()))) &&
            ((this.UCPTurlCpFile==null && other.getUCPTurlCpFile()==null) || 
             (this.UCPTurlCpFile!=null &&
              java.util.Arrays.equals(this.UCPTurlCpFile, other.getUCPTurlCpFile()))) &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              java.util.Arrays.equals(this.address, other.getAddress()))) &&
            ((this.command==null && other.getCommand()==null) || 
             (this.command!=null &&
              java.util.Arrays.equals(this.command, other.getCommand())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getUCPThandle() != null) {
            _hashCode += getUCPThandle().hashCode();
        }
        if (getUCPTuniqueId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTuniqueId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTuniqueId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTreplacementId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTreplacementId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTreplacementId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTprogramId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTprogramId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTprogramId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTgeoPosition() != null) {
            _hashCode += getUCPTgeoPosition().hashCode();
        }
        if (getUCPTlocationId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTlocationId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTlocationId(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getUCPTmaxDynamicFb() != null) {
            _hashCode += getUCPTmaxDynamicFb().hashCode();
        }
        if (getUCPTmaxDynamicDp() != null) {
            _hashCode += getUCPTmaxDynamicDp().hashCode();
        }
        if (getUCPTmaxTxTransactions() != null) {
            _hashCode += getUCPTmaxTxTransactions().hashCode();
        }
        if (getUCPTmaxTxLifetime() != null) {
            _hashCode += getUCPTmaxTxLifetime().hashCode();
        }
        if (getUCPTlocal() != null) {
            _hashCode += getUCPTlocal().hashCode();
        }
        if (getUCPTapplicationStatus() != null) {
            _hashCode += getUCPTapplicationStatus().hashCode();
        }
        if (getUCPTcommissionStatus() != null) {
            _hashCode += getUCPTcommissionStatus().hashCode();
        }
        if (getUCPTurlImage() != null) {
            _hashCode += getUCPTurlImage().hashCode();
        }
        if (getUCPTurlTemplate() != null) {
            _hashCode += getUCPTurlTemplate().hashCode();
        }
        if (getUCPTdynamic() != null) {
            _hashCode += getUCPTdynamic().hashCode();
        }
        if (getUCPTurlCpFile() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUCPTurlCpFile());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUCPTurlCpFile(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAddress() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddress());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddress(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCommand() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCommand());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCommand(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LON_Device_Cfg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "LON_Device_Cfg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPThandle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPThandle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_UniqueKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTuniqueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTuniqueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTreplacementId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTreplacementId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTprogramId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTprogramId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTgeoPosition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTgeoPosition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlocationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlocationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxDynamicFb");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxDynamicFb"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxDynamicDp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxDynamicDp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxTxTransactions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxTxTransactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTmaxTxLifetime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTmaxTxLifetime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTlocal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTlocal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTapplicationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTapplicationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTcommissionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTcommissionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTurlImage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTurlImage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTurlTemplate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTurlTemplate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTdynamic");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTdynamic"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "E_LonString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UCPTurlCpFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "UCPTurlCpFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>UCPTurlCpFile"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>Address"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("command");
        elemField.setXmlName(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", "Command"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://wsdl.echelon.com/web_services_ns/ilon100/v4.0/message/", ">LON_Device_Cfg>Command"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

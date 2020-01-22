/**
 * KlijentBanke.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model;

public class KlijentBanke  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4696884536042832002L;

	private java.lang.String brojKartice;

    private java.lang.String cvc;

    private java.lang.String datumIsteka;

    private java.lang.String email;

    private java.lang.String ime;

    private java.lang.String prezime;

    private double stanje;

    private java.lang.String tipKartice;

    public KlijentBanke() {
    }

    public KlijentBanke(
           java.lang.String brojKartice,
           java.lang.String cvc,
           java.lang.String datumIsteka,
           java.lang.String email,
           java.lang.String ime,
           java.lang.String prezime,
           double stanje,
           java.lang.String tipKartice) {
           this.brojKartice = brojKartice;
           this.cvc = cvc;
           this.datumIsteka = datumIsteka;
           this.email = email;
           this.ime = ime;
           this.prezime = prezime;
           this.stanje = stanje;
           this.tipKartice = tipKartice;
    }


    /**
     * Gets the brojKartice value for this KlijentBanke.
     * 
     * @return brojKartice
     */
    public java.lang.String getBrojKartice() {
        return brojKartice;
    }


    /**
     * Sets the brojKartice value for this KlijentBanke.
     * 
     * @param brojKartice
     */
    public void setBrojKartice(java.lang.String brojKartice) {
        this.brojKartice = brojKartice;
    }


    /**
     * Gets the cvc value for this KlijentBanke.
     * 
     * @return cvc
     */
    public java.lang.String getCvc() {
        return cvc;
    }


    /**
     * Sets the cvc value for this KlijentBanke.
     * 
     * @param cvc
     */
    public void setCvc(java.lang.String cvc) {
        this.cvc = cvc;
    }


    /**
     * Gets the datumIsteka value for this KlijentBanke.
     * 
     * @return datumIsteka
     */
    public java.lang.String getDatumIsteka() {
        return datumIsteka;
    }


    /**
     * Sets the datumIsteka value for this KlijentBanke.
     * 
     * @param datumIsteka
     */
    public void setDatumIsteka(java.lang.String datumIsteka) {
        this.datumIsteka = datumIsteka;
    }


    /**
     * Gets the email value for this KlijentBanke.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this KlijentBanke.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the ime value for this KlijentBanke.
     * 
     * @return ime
     */
    public java.lang.String getIme() {
        return ime;
    }


    /**
     * Sets the ime value for this KlijentBanke.
     * 
     * @param ime
     */
    public void setIme(java.lang.String ime) {
        this.ime = ime;
    }


    /**
     * Gets the prezime value for this KlijentBanke.
     * 
     * @return prezime
     */
    public java.lang.String getPrezime() {
        return prezime;
    }


    /**
     * Sets the prezime value for this KlijentBanke.
     * 
     * @param prezime
     */
    public void setPrezime(java.lang.String prezime) {
        this.prezime = prezime;
    }


    /**
     * Gets the stanje value for this KlijentBanke.
     * 
     * @return stanje
     */
    public double getStanje() {
        return stanje;
    }


    /**
     * Sets the stanje value for this KlijentBanke.
     * 
     * @param stanje
     */
    public void setStanje(double stanje) {
        this.stanje = stanje;
    }


    /**
     * Gets the tipKartice value for this KlijentBanke.
     * 
     * @return tipKartice
     */
    public java.lang.String getTipKartice() {
        return tipKartice;
    }


    /**
     * Sets the tipKartice value for this KlijentBanke.
     * 
     * @param tipKartice
     */
    public void setTipKartice(java.lang.String tipKartice) {
        this.tipKartice = tipKartice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KlijentBanke)) return false;
        KlijentBanke other = (KlijentBanke) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.brojKartice==null && other.getBrojKartice()==null) || 
             (this.brojKartice!=null &&
              this.brojKartice.equals(other.getBrojKartice()))) &&
            ((this.cvc==null && other.getCvc()==null) || 
             (this.cvc!=null &&
              this.cvc.equals(other.getCvc()))) &&
            ((this.datumIsteka==null && other.getDatumIsteka()==null) || 
             (this.datumIsteka!=null &&
              this.datumIsteka.equals(other.getDatumIsteka()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.ime==null && other.getIme()==null) || 
             (this.ime!=null &&
              this.ime.equals(other.getIme()))) &&
            ((this.prezime==null && other.getPrezime()==null) || 
             (this.prezime!=null &&
              this.prezime.equals(other.getPrezime()))) &&
            this.stanje == other.getStanje() &&
            ((this.tipKartice==null && other.getTipKartice()==null) || 
             (this.tipKartice!=null &&
              this.tipKartice.equals(other.getTipKartice())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBrojKartice() != null) {
            _hashCode += getBrojKartice().hashCode();
        }
        if (getCvc() != null) {
            _hashCode += getCvc().hashCode();
        }
        if (getDatumIsteka() != null) {
            _hashCode += getDatumIsteka().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getIme() != null) {
            _hashCode += getIme().hashCode();
        }
        if (getPrezime() != null) {
            _hashCode += getPrezime().hashCode();
        }
        _hashCode += new Double(getStanje()).hashCode();
        if (getTipKartice() != null) {
            _hashCode += getTipKartice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KlijentBanke.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model", "KlijentBanke"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("brojKartice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "brojKartice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cvc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "cvc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datumIsteka");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "datumIsteka"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "ime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prezime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "prezime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stanje");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "stanje"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipKartice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "tipKartice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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

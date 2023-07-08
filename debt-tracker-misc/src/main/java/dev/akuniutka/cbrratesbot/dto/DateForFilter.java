package dev.akuniutka.cbrratesbot.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetCursOnDateXML", namespace = "http://web.cbr.ru/")
public class DateForFilter {
    @XmlElement(name = "On_date", required = true, namespace = "http://web.cbr.ru/")
    private XMLGregorianCalendar value;
}
